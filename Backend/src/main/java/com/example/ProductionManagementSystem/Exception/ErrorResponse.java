package com.example.ProductionManagementSystem.Exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


@Data
public class ErrorResponse {

    public static final ErrorResponse BAD_REQUEST = new ErrorResponse(HttpStatus.BAD_REQUEST, "S001", "Bad requestsss.");
    public static final ErrorResponse NOT_FOUND = new ErrorResponse(HttpStatus.NOT_FOUND, "S002", "Not found.");
    public static final ErrorResponse UNAUTHORIZED = new ErrorResponse(HttpStatus.UNAUTHORIZED, "S003", "Unauthorized.");
    public static final ErrorResponse FORBIDDEN = new ErrorResponse(HttpStatus.FORBIDDEN, "S004", "Forbidden.");
    public static final ErrorResponse SERVICE_ERROR = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "S005", "Internal Server Error.");
    public static final ErrorResponse VALIDATION_ERROR = new ErrorResponse(HttpStatus.BAD_REQUEST, "S006", "Validation error.");
    public static final ErrorResponse NO_CUSTOMER = new ErrorResponse(HttpStatus.BAD_REQUEST, "S007", "Хэрэглэгч олдсонгүй.");
    public static final ErrorResponse AUTH = new ErrorResponse(HttpStatus.BAD_REQUEST, "S008", "Нэвтрэх нэр эсвэл нууц үг таарахгүй байна.");
    public static final ErrorResponse METHOD_NOT_ALLOWED = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, "S009", "Method not allowed.");
    public static final ErrorResponse EMAIL_ALREADY_EXIST = new ErrorResponse(HttpStatus.BAD_REQUEST, "S010", "Аль хэдийн үүссэн мейл байна.");
    public static final ErrorResponse SIGNATURE_NOT_ALLOWED = new ErrorResponse(HttpStatus.UNAUTHORIZED, "S011", "Signature is not allowed.");
    public static final ErrorResponse MALFORMED_JWT_EXCEPTION = new ErrorResponse(HttpStatus.UNAUTHORIZED, "S012", "Malformed token.");
    public static final ErrorResponse CANNOT_ACCESS = new ErrorResponse(HttpStatus.UNAUTHORIZED, "S013", "Cannot access this.");
    public static final ErrorResponse NO_CONTENT = new ErrorResponse(HttpStatus.NO_CONTENT, "S013", "Хоосон утга.");
    public static final ErrorResponse VALID_EMAIL = new ErrorResponse(HttpStatus.BAD_REQUEST, "S014", "Мейл формат буруу байна.");
    public static final ErrorResponse ROLE_NOT_FOUND = new ErrorResponse(HttpStatus.BAD_REQUEST, "S015", "Role олдсонгүй.");
    public static final ErrorResponse JWT_EXPIRED = new ErrorResponse(HttpStatus.UNAUTHORIZED, "S016", "JWT token has expired.");
    public static final ErrorResponse COLOR_NOT_FOUND = new ErrorResponse(HttpStatus.BAD_REQUEST, "S017", "Өнгө олдсонгүй.");
    public static final ErrorResponse NO_FIBER_RECEIVE = new ErrorResponse(HttpStatus.BAD_REQUEST, "S018", "FiberReceive олдсонгүй.");
    public static final ErrorResponse NO_FIBER_TYPE = new ErrorResponse(HttpStatus.BAD_REQUEST, "S019", "FiberType олдсонгүй.");
    public static final ErrorResponse NO_FACTORY_PROCESS = new ErrorResponse(HttpStatus.BAD_REQUEST, "S020", "FactoryProcess олдсонгүй.");
    public static final ErrorResponse NO_ORDER = new ErrorResponse(HttpStatus.BAD_REQUEST, "S021", "Захиалга олдсонгүй.");
    public static final ErrorResponse NO_FIBER_COLOR = new ErrorResponse(HttpStatus.BAD_REQUEST, "S022", "FiberColor олдсонгүй.");
    public static final ErrorResponse EXCEEDS_WEIGHT = new ErrorResponse(HttpStatus.BAD_REQUEST, "S023", "Хүлээн авах нийт жин захиалгын жингээс хэтэрсэн байна.");
    public static final ErrorResponse NO_PROCESS_RECEIVE = new ErrorResponse(HttpStatus.BAD_REQUEST, "S024", "ProcessReceive олдсонгүй.");
    public static final ErrorResponse EXCEEDS_AVAILABLE_WEIGHT = new ErrorResponse(HttpStatus.BAD_REQUEST, "S025", "Exceeded available weight.");
    public static final ErrorResponse NO_PROCESS = new ErrorResponse(HttpStatus.BAD_REQUEST, "S026", "Процесс олдсонгүй.");
    public static final ErrorResponse INSUFFICIENT_INVENTORY = new ErrorResponse(HttpStatus.BAD_REQUEST, "S027", "Inventory is insufficient.");
    public static final ErrorResponse NO_INVENTORY = new ErrorResponse(HttpStatus.BAD_REQUEST, "S028", "Inventory not found.");
    public static final ErrorResponse INVALID_WEIGHT = new ErrorResponse(HttpStatus.BAD_REQUEST, "S029", "Invalid weight.");
    public static final ErrorResponse NO_USER_FOUND = new ErrorResponse(HttpStatus.BAD_REQUEST, "S030", "Хэрэглэгч олдсонгүй.");
    public static final ErrorResponse IN_PROGRESS =  new ErrorResponse(HttpStatus.BAD_REQUEST, "S031", "Процессийг эхлүүлсэн байна.");
    public static final ErrorResponse NO_INVENTORY_TO_DELIVER = new ErrorResponse(HttpStatus.BAD_REQUEST, "S032", "Хүлээлгэн өгөх түүхий эд олдсонгүй");
    public static final ErrorResponse NO_INVENTORY_SELECTED = new ErrorResponse(HttpStatus.BAD_REQUEST, "S033", "Хүлээлгэн өгөх түүхий эд сонгоогүй байна.");
    public static final ErrorResponse OUTPUT_MATERIAL_NOT_CONFIGURED = new ErrorResponse(HttpStatus.BAD_REQUEST, "S034", "Процессд гаралтын материал тохируулагдаагүй байна.");

    @JsonIgnore
    private HttpStatusCode status;
    private String code;
    private String message;

    public ErrorResponse(HttpStatusCode status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResponse [code=" + code + ", message=" + message + "]";
    }
}
