package www.hbj.cloud.baselibrary.ngr_library.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class GsonPHPConverterFactory extends Converter.Factory {

    public static GsonPHPConverterFactory create() {
        return create(new Gson());
    }

    public static GsonPHPConverterFactory create(Gson gson) {
        return new GsonPHPConverterFactory(gson);
    }

    private final Gson gson;

    private GsonPHPConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new GsonResponseBodyPhpConverter<>(gson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonResponseBodyPhpConverter<>(gson, (Type) adapter);
    }
}
