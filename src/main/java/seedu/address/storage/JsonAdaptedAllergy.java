package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.medicine.Medicine;

/**
 * Jackson-friendly version of {@link Allergy}.
 */
class JsonAdaptedAllergy {

    private final String allergyName;

    /**
     * Constructs a {@code JsonAdaptedAllergy} with the given {@code allergyName}.
     */
    @JsonCreator
    public JsonAdaptedAllergy(String allergyName) {
        this.allergyName = allergyName;
    }

    /**
     * Converts a given {@code Allergy} into this class for Jackson use.
     */
    public JsonAdaptedAllergy(Allergy source) {
        allergyName = source.allergy.getMedicineName();
    }

    @JsonValue
    public String getAllergyName() {
        return allergyName;
    }

    /**
     * Converts this Jackson-friendly adapted allergy object into the model's {@code Allergy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted allergy.
     */
    public Allergy toModelType() throws IllegalValueException {
        if (!Allergy.isValidAllergyName(allergyName)) {
            throw new IllegalValueException(Allergy.MESSAGE_CONSTRAINTS);
        }
        return new Allergy(new Medicine(allergyName));
    }

}
