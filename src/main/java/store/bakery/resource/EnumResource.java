package store.bakery.resource;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.bakery.model.appEnum.PaymentMethodEnum;
import store.bakery.model.appEnum.SizeEnum;
import store.bakery.model.appEnum.StatusEnum;
import store.bakery.util.EnumUtils;

import java.util.List;

@RestController
@RequestMapping("/api/enum")
public class EnumResource {

    @GetMapping("/paymentMethod")
    public ResponseEntity<?> getAllSchoolTypesEnums() {
        List<PaymentMethodEnum> paymentMethodEnums = EnumUtils.getEnumValues(PaymentMethodEnum.class);
        return ResponseEntity.ok(paymentMethodEnums);
    }
    @GetMapping  ("/status")
    public ResponseEntity<?> getAllAdmissionAgeEnums() {
        List<StatusEnum> statusEnums = EnumUtils.getEnumValues(StatusEnum.class);
        return ResponseEntity.ok(statusEnums);
    }
    @GetMapping  ("/size")
    public ResponseEntity<?> getEducationMethodEnums() {
        List<SizeEnum> sizeEnums = EnumUtils.getEnumValues(SizeEnum.class);
        return ResponseEntity.ok(sizeEnums);
    }
}
