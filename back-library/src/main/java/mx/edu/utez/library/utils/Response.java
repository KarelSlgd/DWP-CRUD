package mx.edu.utez.library.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Response<T>{
    T data;
    Boolean error;
    int StatusCode;
    String message;
}
