package www.hbj.cloud.baselibrary.ngr_library;

import java.io.Serializable;

/**
 * @author zw
 * @date 2019/12/25.
 * <p>
 * Email：861126420@qq.com
 * Description：登陆
 */
public class MessageItem implements Serializable {


    /**
     * display_type : notification
     * body : {"after_open":"go_app","ticker":"标题","title":"标题","play_sound":"true","play_lights":"false","play_vibrate":"false","text":"内容"}
     * msg_id : ua8j52b158391371715711
     */

    private String display_type;
    private BodyBean body;
    private String msg_id;

    public String getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(String display_type) {
        this.display_type = display_type;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public static class BodyBean {
        /**
         * after_open : go_app
         * ticker : 标题
         * title : 标题
         * play_sound : true
         * play_lights : false
         * play_vibrate : false
         * text : 内容
         */

        private String after_open;
        private String ticker;
        private String title;
        private String play_sound;
        private String play_lights;
        private String play_vibrate;
        private String text;

        public String getAfter_open() {
            return after_open;
        }

        public void setAfter_open(String after_open) {
            this.after_open = after_open;
        }

        public String getTicker() {
            return ticker;
        }

        public void setTicker(String ticker) {
            this.ticker = ticker;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPlay_sound() {
            return play_sound;
        }

        public void setPlay_sound(String play_sound) {
            this.play_sound = play_sound;
        }

        public String getPlay_lights() {
            return play_lights;
        }

        public void setPlay_lights(String play_lights) {
            this.play_lights = play_lights;
        }

        public String getPlay_vibrate() {
            return play_vibrate;
        }

        public void setPlay_vibrate(String play_vibrate) {
            this.play_vibrate = play_vibrate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
