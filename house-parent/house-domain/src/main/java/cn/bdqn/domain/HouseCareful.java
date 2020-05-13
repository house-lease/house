package cn.bdqn.domain;

/**
 * 房屋详细信息
 */
public class HouseCareful {
    private Integer id;

    private Integer houseId;

    private Integer chuang;

    private Integer shafa;

    private Integer kongtiao;

    private Integer ranqi;

    private Integer xiyiji;

    private Integer kuandai;

    private Integer dianshi;

    private Integer bingxiang;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getChuang() {
        return chuang;
    }

    public void setChuang(Integer chuang) {
        this.chuang = chuang;
    }

    public Integer getShafa() {
        return shafa;
    }

    public void setShafa(Integer shafa) {
        this.shafa = shafa;
    }

    public Integer getKongtiao() {
        return kongtiao;
    }

    public void setKongtiao(Integer kongtiao) {
        this.kongtiao = kongtiao;
    }

    public Integer getRanqi() {
        return ranqi;
    }

    public void setRanqi(Integer ranqi) {
        this.ranqi = ranqi;
    }

    public Integer getXiyiji() {
        return xiyiji;
    }

    public void setXiyiji(Integer xiyiji) {
        this.xiyiji = xiyiji;
    }

    public Integer getKuandai() {
        return kuandai;
    }

    public void setKuandai(Integer kuandai) {
        this.kuandai = kuandai;
    }

    public Integer getDianshi() {
        return dianshi;
    }

    public void setDianshi(Integer dianshi) {
        this.dianshi = dianshi;
    }

    public Integer getBingxiang() {
        return bingxiang;
    }

    public void setBingxiang(Integer bingxiang) {
        this.bingxiang = bingxiang;
    }

    @Override
    public String toString() {
        return "HouseCareful{" +
                "id=" + id +
                ", houseId=" + houseId +
                ", chuang=" + chuang +
                ", shafa=" + shafa +
                ", kongtiao=" + kongtiao +
                ", ranqi=" + ranqi +
                ", xiyiji=" + xiyiji +
                ", kuandai=" + kuandai +
                ", dianshi=" + dianshi +
                ", bingxiang=" + bingxiang +
                '}';
    }
}