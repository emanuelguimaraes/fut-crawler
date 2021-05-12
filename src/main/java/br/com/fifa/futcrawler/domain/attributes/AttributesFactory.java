package br.com.fifa.futcrawler.domain.attributes;

public class AttributesFactory {

    public static Pace createPaceAttributes(int aceleracao, int sprint) {
        return new Pace(aceleracao, sprint);
    }

    public static Shooting createShootingAttributes(int posicionamento, int finalizacao, int potencia, int chuteDeLonge,
                                                    int chutesAcrobaticos, int penalti) {
        return new Shooting(posicionamento, finalizacao, potencia, chuteDeLonge, chutesAcrobaticos, penalti);
    }

    public static Passing createPassingAttributes(int visao, int cruzamento, int cobrancaFalta, int passeCurto, int passeLongo,
                                                  int efeito) {
        return new Passing(visao, cruzamento, cobrancaFalta, passeCurto, passeLongo, efeito);
    }

    public static Dribbling createDribblingAttributes(int agilidade, int equilibrio, int reflexos, int controleDeBola, int drible,
                                                      int compostura) {
        return new Dribbling(agilidade, equilibrio, reflexos, controleDeBola, drible, compostura);
    }

    public static Defending createDefendingAttributes(int interceptar, int cabeceamento, int marcacao, int carrinho, int corteEmPe) {
        return new Defending(interceptar, cabeceamento, marcacao, carrinho, corteEmPe);
    }

    public static Physicality createPhysicalityAttributes(int salto, int resistencia, int forca, int agressividade) {
        return new Physicality(salto, resistencia, forca, agressividade);
    }
}
