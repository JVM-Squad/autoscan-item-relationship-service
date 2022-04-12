//
// Copyright (c) 2021 Copyright Holder (Catena-X Consortium)
//
// See the AUTHORS file(s) distributed with this work for additional
// information regarding authorship.
//
// See the LICENSE file(s) distributed with this work for
// additional information regarding license terms.
//
package net.catenax.irs.component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import net.catenax.irs.annotations.ExcludeFromCodeCoverageGeneratedReport;

/**
 * Relationship
 */
@Value
@Jacksonized
@Builder(toBuilder = true)
@AllArgsConstructor
@ExcludeFromCodeCoverageGeneratedReport
public class Relationship {

    private String catenaXId;

    private Job childItem;

    private Job parentItem;

}
