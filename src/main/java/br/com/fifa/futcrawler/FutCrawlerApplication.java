package br.com.fifa.futcrawler;

import br.com.fifa.futcrawler.application.crawler.SaveCardsByCrawler;
import br.com.fifa.futcrawler.infrastructure.card.CardRepositoryImpl;
import br.com.fifa.futcrawler.infrastructure.crawler.CrawlerJsoup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FutCrawlerApplication {

	public static final int FIVE_MINUTES = 300000;
	private Logger logger = LogManager.getLogger(FutCrawlerApplication.class);

	private static final String SITE_URL = "https://www.futbin.com/21/players?page=";

	@Autowired
	private CardRepositoryImpl repository;

	@Autowired
	private CrawlerJsoup crawlerJsoup;

	public static void main(String[] args) {
		SpringApplication.run(FutCrawlerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			SaveCardsByCrawler service = new SaveCardsByCrawler(repository, crawlerJsoup);

			for (int index = 264; index <= 668; index++) {
				try {
					service.execute(SITE_URL.concat(Integer.toString(index)));

					logger.info(String.format("Carregado elementos da requisição %s",
							SITE_URL.concat(Integer.toString(index))));

				} catch (Exception e) {
					logger.error(e.getMessage());
					Thread.sleep(FIVE_MINUTES);
					index--;
				}
			}
		};
	}
}
