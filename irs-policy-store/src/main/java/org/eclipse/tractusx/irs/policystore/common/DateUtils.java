/********************************************************************************
 * Copyright (c) 2022,2024 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 * Copyright (c) 2021,2024 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ********************************************************************************/
package org.eclipse.tractusx.irs.policystore.common;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Date utilities.
 */
public final class DateUtils {

    private DateUtils() {
        // private constructor (utility  class)
    }

    public static boolean isDateBefore(final OffsetDateTime dateTime, final String referenceDateString) {
        if (isDateWithoutTime(referenceDateString)) {
            return dateTime.isBefore(toOffsetDateTimeAtStartOfDay(referenceDateString));
        } else {
            return dateTime.isBefore(OffsetDateTime.parse(referenceDateString));
        }
    }

    public static boolean isDateAfter(final OffsetDateTime dateTime, final String referenceDateString) {
        if (isDateWithoutTime(referenceDateString)) {
            return dateTime.isAfter(toOffsetDateTimeAtEndOfDay(referenceDateString));
        } else {
            return dateTime.isAfter(OffsetDateTime.parse(referenceDateString));
        }
    }

    public static OffsetDateTime toOffsetDateTimeAtStartOfDay(final String dateString) {
        return LocalDate.parse(dateString).atStartOfDay().atOffset(ZoneOffset.UTC);
    }

    public static OffsetDateTime toOffsetDateTimeAtEndOfDay(final String dateString) {
        return LocalDate.parse(dateString).atTime(LocalTime.MAX).atOffset(ZoneOffset.UTC);
    }

    public static boolean isDateWithoutTime(final String referenceDateString) {
        try {
            DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(referenceDateString);
            return true;
        } catch (DateTimeParseException e) {
            try {
                OffsetDateTime.parse(referenceDateString, DateTimeFormatter.ISO_DATE);
                return true;
            } catch (DateTimeParseException e2) {
                try {
                    OffsetDateTime.parse(referenceDateString, DateTimeFormatter.ISO_DATE_TIME);
                    return false;
                } catch (DateTimeParseException e3) {
                    throw new IllegalArgumentException("Invalid date format: " + referenceDateString);
                }
            }
        }
    }
}
