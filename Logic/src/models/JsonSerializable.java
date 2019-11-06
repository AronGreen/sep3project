//package models;
//
//        import com.google.gson.Gson;
//        import com.google.gson.GsonBuilder;
//
//public abstract class JsonSerializable<T> {
//
//    public String toJson(){
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        return gson.toJson(this);
//    }
//
//    public T fromJson(String json){
//        System.out.println(json);
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        return gson.fromJson(json, T.class);
//    }
//}
