package br.com.fifa.futcrawler.infrastructure.price.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "prices")
@JsonIgnoreProperties
public class PricesDTO {

    @JsonProperty("xbox")
    private ConsoleDTO xbox;

    @JsonProperty("ps")
    private ConsoleDTO ps;

    @JsonProperty("pc")
    private ConsoleDTO pc;

    public ConsoleDTO getXbox() {
        return xbox;
    }

    public void setXbox(ConsoleDTO xbox) {
        this.xbox = xbox;
    }

    public ConsoleDTO getPs() {
        return ps;
    }

    public void setPs(ConsoleDTO ps) {
        this.ps = ps;
    }

    public ConsoleDTO getPc() {
        return pc;
    }

    public void setPc(ConsoleDTO pc) {
        this.pc = pc;
    }
}
