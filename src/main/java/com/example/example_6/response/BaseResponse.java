package com.example.example_6.response;

import lombok.Builder;
import lombok.Data;

/**
 * Controllarlar icin temel response sinifidir.
 * @param <T> Entitylerin Response nesneleri atanir.
 */
@Data
@Builder
public class BaseResponse<T> {
    private Integer status;
    private String message;
    private T response;

    /**
     * Basarili bir request sonucunda request ile ilgili status message ve response ile donen datayi icerir.
     * @param data http tarafina gonderilen requesti icerir
     * @param message request basarili olursa gonderilecek message bilgilerini icerir.
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> success(T data, String message) {
        return BaseResponse.<T>builder()
                .status(200)
                .message(message)
                .response(data)
                .build();
    }

    /**
     * Basarisiz bir request sonucunda status ve message bilgilerini icerir.
     * @param message
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> error(String message) {
        return BaseResponse.<T>builder()
                .status(400)
                .message(message)
                .build();
    }
}
