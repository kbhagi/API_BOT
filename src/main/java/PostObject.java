import java.util.HashMap;

public class PostObject {

    @Override
    public String toString() {
        return "{" +
                "\"key_1\":"+  value_1+", " +
                "\"key_2\":"+value_2+
                "}";
    }

    private String cf_user_id;
    private String role_list;
    public PostObject(String value_1, String value_2) {
            this.value_1=value_1;
            this.value_1=value_2;
    }



}
