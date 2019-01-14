import com.google.gson.annotations.SerializedName;



public class User {

    public String getPhone() {
        return value1;
    }

    public void setPhone(String value1) {
        this.value1 = value1;
    }

    public String getRegistration_key() {
        return value2;
    }

    public void setRegistration_key(String value2) {
        this.value2 = value2;
    }

    public String getEmail() {
        return value3;
    }

    public void setEmail(String email) {
        this.value3 = value3;
    }

    public String getCf_user_id() {
        return value4;
    }

    public void setCf_user_id(String cf_user_id) {
        this.value4 = value4;
    }

    @Override
    public String toString() {
        return "Object{" +
                "key='" + value1 + '\'' +
                ", key1='" + value2 + '\'' +
                ", key2='" + value3 + '\'' +
                ", key3='" + value4 + '\'' +
                '}';
    }

    String value1;
    String value2;
    String value3;
    String value4;

}
