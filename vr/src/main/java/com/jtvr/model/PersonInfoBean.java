package com.jtvr.model;

/**
 * Created by jt on 2017/4/9.
 */
public class PersonInfoBean {


    /**
     * status : 99
     * business : {"phone":"efc55ff7-bd06-4e0d-8ad2-72dabbc483c5","img":"http://192.168.1.110:8080/vrtext/upload/user/2017/04/10/20170410143259464_small.png","nick":"111","address":"北京市","city":"","county":"","dateOfBirty":"2017-04-10","email":"1111@","fires":"911100","province":"","sexMen":"男","qqMen":"","profession":"农民"}
     */

    private String status;
    private BusinessBean business;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BusinessBean getBusiness() {
        return business;
    }

    public void setBusiness(BusinessBean business) {
        this.business = business;
    }

    public static class BusinessBean {
        /**
         * phone : efc55ff7-bd06-4e0d-8ad2-72dabbc483c5
         * img : http://192.168.1.110:8080/vrtext/upload/user/2017/04/10/20170410143259464_small.png
         * nick : 111
         * address : 北京市
         * city :
         * county :
         * dateOfBirty : 2017-04-10
         * email : 1111@
         * fires : 911100
         * province :
         * sexMen : 男
         * qqMen :
         * profession : 农民
         */

        private String phone;
        private String img;
        private String nick;
        private String address;
        private String city;
        private String county;
        private String dateOfBirty;
        private String email;
        private String fires;
        private String province;
        private String sexMen;
        private String qqMen;
        private String profession;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getDateOfBirty() {
            return dateOfBirty;
        }

        public void setDateOfBirty(String dateOfBirty) {
            this.dateOfBirty = dateOfBirty;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFires() {
            return fires;
        }

        public void setFires(String fires) {
            this.fires = fires;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSexMen() {
            return sexMen;
        }

        public void setSexMen(String sexMen) {
            this.sexMen = sexMen;
        }

        public String getQqMen() {
            return qqMen;
        }

        public void setQqMen(String qqMen) {
            this.qqMen = qqMen;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }
    }
}
