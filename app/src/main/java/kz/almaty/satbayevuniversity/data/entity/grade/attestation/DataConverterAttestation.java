package kz.almaty.satbayevuniversity.data.entity.grade.attestation;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;

public class DataConverterAttestation implements Serializable {
    Gson gson = new Gson();

    @TypeConverter
    public String fromAttestationDetailList(AttestationDetail attestationDetails) {
        if (attestationDetails == null) {
            return (null);
        }
        Type type = new TypeToken<AttestationDetail>() {}.getType();
        String json = gson.toJson(attestationDetails, type);
        return json;
    }

    @TypeConverter
    public AttestationDetail toAttestationDetailList(String attestationDetailsString) {
        if (attestationDetailsString == null) {
            return (null);
        }
        Type type = new TypeToken<AttestationDetail>() {}.getType();
        AttestationDetail attestationDetails = gson.fromJson(attestationDetailsString, type);
        return attestationDetails;
    }
}

