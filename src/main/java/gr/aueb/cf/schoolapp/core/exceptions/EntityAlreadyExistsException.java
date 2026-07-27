package gr.aueb.cf.schoolapp.core.exceptions;

import gr.aueb.cf.schoolapp.dto.RegionReadOnlyDTO;

public class EntityAlreadyExistsException extends Exception {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
