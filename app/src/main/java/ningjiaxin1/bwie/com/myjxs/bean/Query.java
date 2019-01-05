package ningjiaxin1.bwie.com.myjxs.bean;

import java.util.List;

public class Query {

    /**
     * message : 查询成功
     * result : [{"commodityId":175,"commodityName":"倍晶 苹果微软联想惠普华硕戴尔宏基笔记本电脑包13英寸内胆14手提15单肩15.6小新11保护套 粉红色手提包+同色电源小包 15.6英寸","masterPic":"http://172.17.8.100/images/small/commodity/xbsd/dnb/6/1.jpg","price":119,"saleNum":0},{"commodityId":172,"commodityName":"艾奔AspenSpor新款大容量男士双肩包学生书包防盗电脑包充电旅行背包 黑色_标准版","masterPic":"http://172.17.8.100/images/small/commodity/xbsd/dnb/3/1.jpg","price":89,"saleNum":0},{"commodityId":188,"commodityName":"赫登尔（herder）双肩包男时尚旅行背包学生书包电脑包大容量潮流男包0902A","masterPic":"http://172.17.8.100/images/small/commodity/xbsd/sjb/5/1.jpg","price":169,"saleNum":0},{"commodityId":118,"commodityName":" 新款 iPad 128G WIFI 版 平板电脑","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/yyyl/5/1.jpg","price":2988,"saleNum":0},{"commodityId":184,"commodityName":"瑞士军刀双肩包男士背包新款大容量休闲商务旅行电脑包学生书包 USb充电包","masterPic":"http://172.17.8.100/images/small/commodity/xbsd/sjb/1/1.jpg","price":99,"saleNum":0}]
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 175
         * commodityName : 倍晶 苹果微软联想惠普华硕戴尔宏基笔记本电脑包13英寸内胆14手提15单肩15.6小新11保护套 粉红色手提包+同色电源小包 15.6英寸
         * masterPic : http://172.17.8.100/images/small/commodity/xbsd/dnb/6/1.jpg
         * price : 119
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
