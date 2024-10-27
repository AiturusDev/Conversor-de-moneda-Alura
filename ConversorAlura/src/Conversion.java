import com.google.gson.annotations.SerializedName;

public record Conversion(
        @SerializedName("conversion_rate")String conversion_rate ,
        @SerializedName("conversion_result")String conversion_result,
        @SerializedName("base_code")String base_code,
        @SerializedName("target_code")String target_code
        ) {
}
