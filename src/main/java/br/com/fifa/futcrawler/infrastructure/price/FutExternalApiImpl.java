package br.com.fifa.futcrawler.infrastructure.price;

import br.com.fifa.futcrawler.application.price.FutExternalApi;
import br.com.fifa.futcrawler.domain.price.Price;
import br.com.fifa.futcrawler.infrastructure.price.dto.PricesDTO;
import br.com.fifa.futcrawler.infrastructure.price.dto.ResponseApiPriceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class FutExternalApiImpl implements FutExternalApi {

    private final Logger logger = LogManager.getLogger(FutExternalApiImpl.class);

    private static final String URL = "https://www.futbin.com/21/playerPrices?player={player}";
    private static final String PLAYER_PARAM = "player";
    private static final String PRICES_JSON_ELEMENT = "prices";
    private static final String XBOX = "xbox";
    private static final String PLAYSTATION = "ps";
    private static final String PC = "pc";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Price getCardPrice(Long idCard, String console) {
        try {
            Price price = new Price();
            Map<String, Long> params = Map.of(PLAYER_PARAM, idCard);

            ResponseEntity<ResponseApiPriceDTO> response = restTemplate
                    .getForEntity(URL, ResponseApiPriceDTO.class, params);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                PricesDTO pricesDTO = convertFromPricesDTO(response.getBody(), idCard);

                switch (console) {
                    case XBOX:
                        price = new Price(
                                pricesDTO.getXbox().getlCPrice(),
                                pricesDTO.getXbox().getMinPrice(),
                                pricesDTO.getXbox().getMaxPrice());
                        break;
                    case PLAYSTATION:
                        price = new Price(
                                pricesDTO.getPs().getlCPrice(),
                                pricesDTO.getPs().getMinPrice(),
                                pricesDTO.getPs().getMaxPrice());
                        break;
                    case PC:
                        price = new Price(
                                pricesDTO.getPc().getlCPrice(),
                                pricesDTO.getPc().getMinPrice(),
                                pricesDTO.getPc().getMaxPrice());
                        break;
                }
            } else {
                logger.error(response.getStatusCode());
                throw new RuntimeException(String.format(
                        "Não foi possível encontrar o valor atual para o card de ID = %s",
                        idCard));
            }

            return price;

        } catch (RestClientException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(String.format(
                    "Ocorreu um erro durante a execução do endpoint de preços para o card de ID = %s",
                    idCard));
        }
    }

    private PricesDTO convertFromPricesDTO(ResponseApiPriceDTO apiPriceDTO, Long idCard) {
        ObjectMapper mapper = new ObjectMapper();
        LinkedHashMap map = (LinkedHashMap)
                ((LinkedHashMap) apiPriceDTO.getDetails()
                .get(idCard.toString()))
                .get(PRICES_JSON_ELEMENT);

        return mapper.convertValue(map, PricesDTO.class);
    }
}
