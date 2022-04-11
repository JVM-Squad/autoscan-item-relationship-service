//
// Copyright (c) 2021 Copyright Holder (Catena-X Consortium)
//
// See the AUTHORS file(s) distributed with this work for additional
// information regarding authorship.
//
// See the LICENSE file(s) distributed with this work for
// additional information regarding license terms.
//
//

package net.catenax.irs.dto;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

/**
 * AssemblyPartRelationshipDTO model used for internal application use
 */
@Data
@Builder(toBuilder = true, setterPrefix = "with")
@JsonDeserialize(builder = AssemblyPartRelationshipDTO.AssemblyPartRelationshipDTOBuilder.class)
public class AssemblyPartRelationshipDTO {
    /**
     * catenaXId
     */
    private String catenaXId;

    /**
     * childParts
     */
    private Set<ChildDataDTO> childParts;

    /**
     * Builder class
     */
    @JsonPOJOBuilder(withPrefix = "with")
    public static class AssemblyPartRelationshipDTOBuilder {

    }
}
