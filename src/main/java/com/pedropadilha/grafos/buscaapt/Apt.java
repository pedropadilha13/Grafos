package com.pedropadilha.grafos.buscaapt;

/**
 * @author pedropadilha13
 */
public class Apt {
    private final String id;
    private double[] coords; // [5]
    private Integer area; // [4]
    private Integer quartos; // [4]
    private Integer suites; // [2]
    private Integer banheiros; // [2]
    private Integer vagas; // [3]
    private Boolean isPetFriendly; // [3]
    private Integer andar; // [1]

    public Apt(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double[] getCoords() {
        return coords;
    }

    public void setCoords(double[] coords) {
        this.coords = coords;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getQuartos() {
        return quartos;
    }

    public void setQuartos(Integer quartos) {
        this.quartos = quartos;
    }

    public Integer getSuites() {
        return suites;
    }

    public void setSuites(Integer suites) {
        this.suites = suites;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Integer getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(Integer banheiros) {
        this.banheiros = banheiros;
    }

    public Boolean getPetFriendly() {
        return isPetFriendly;
    }

    public void setPetFriendly(Boolean petFriendly) {
        isPetFriendly = petFriendly;
    }

    public Integer getAndar() {
        return andar;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }
}
