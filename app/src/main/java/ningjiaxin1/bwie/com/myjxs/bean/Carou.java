package ningjiaxin1.bwie.com.myjxs.bean;

import java.util.List;

public class Carou {
    private String message;
    private String status;
    private List<Result> result;

    public Carou(String message, String status, List<Result> result) {
        this.message = message;
        this.status = status;
        this.result = result;
    }

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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result {
        private int rank;
        private String jumpUrl;
        private String title;
        private String imageUrl;

        public Result(int rank, String jumpUrl, String title, String imageUrl) {
            this.rank = rank;
            this.jumpUrl = jumpUrl;
            this.title = title;
            this.imageUrl = imageUrl;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
