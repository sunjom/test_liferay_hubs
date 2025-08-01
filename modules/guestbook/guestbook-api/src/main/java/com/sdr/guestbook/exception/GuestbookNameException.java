/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
package com.sdr.guestbook.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author devsky
 */
public class GuestbookNameException extends PortalException {

	public GuestbookNameException() {
	}

	public GuestbookNameException(String msg) {
		super(msg);
	}

	public GuestbookNameException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public GuestbookNameException(Throwable throwable) {
		super(throwable);
	}

}