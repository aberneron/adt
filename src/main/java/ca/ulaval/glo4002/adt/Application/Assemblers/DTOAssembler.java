package ca.ulaval.glo4002.adt.Application.Assemblers;

import java.util.Collection;

public interface DTOAssembler<DTOObject, DomainObject> {
    DTOObject writeDTO(DomainObject obj);
    Collection<DTOObject> writeDTOCollection(Collection<DomainObject> objs);
}
