/********************************************************************************
 * Copyright (c) 2021,2022,2023
 *       2022: ZF Friedrichshafen AG
 *       2022: ISTOS GmbH
 *       2022,2023: Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *       2022,2023: BOSCH AG
 * Copyright (c) 2021,2022,2023 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0. *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ********************************************************************************/
package org.eclipse.tractusx.irs.aaswrapper.registry.domain;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.eclipse.dataspaceconnector.spi.types.domain.edr.EndpointDataReference;
import org.eclipse.tractusx.irs.edc.client.EdcSubmodelFacade;
import org.eclipse.tractusx.irs.edc.client.exceptions.EdcClientException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

/**
 * Service that use edc client to make calls to edc connector endpoints
 * to find DigitalTwinRegistry asset
 */
@Service
@RequiredArgsConstructor
public class EndpointDataForConnectorsService {

    private static final String DT_REGISTRY_ASSET_TYPE = "asset:prop:type";
    private static final String DT_REGISTRY_ASSET_VALUE = "data.core.digitalTwinRegistry";

    private final EdcSubmodelFacade edcSubmodelFacade;

    /* package */ List<EndpointDataReference> findEndpointDataForConnectors(final List<String> connectorEndpoints) {
        return connectorEndpoints.stream().map(connector -> {
            try {
                return edcSubmodelFacade.getEndpointReferenceForAsset(connector, DT_REGISTRY_ASSET_TYPE,
                        DT_REGISTRY_ASSET_VALUE);
            } catch (EdcClientException e) {
                throw new RestClientException(e.getMessage(), e);
            }
        }).toList();
    }

}
