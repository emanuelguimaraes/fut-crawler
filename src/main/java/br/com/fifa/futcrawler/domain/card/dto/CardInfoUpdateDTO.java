package br.com.fifa.futcrawler.domain.card.dto;

public class CardInfoUpdateDTO {

    private Long id;
    private Long idResource;

    public CardInfoUpdateDTO(Long id, Long idResource) {
        this.id = id;
        this.idResource = idResource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdResource() {
        return idResource;
    }

    public void setIdResource(Long idResource) {
        this.idResource = idResource;
    }
}
