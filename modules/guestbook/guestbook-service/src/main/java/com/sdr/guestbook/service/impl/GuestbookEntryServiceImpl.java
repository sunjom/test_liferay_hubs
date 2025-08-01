/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.sdr.guestbook.service.impl;

import com.liferay.portal.aop.AopService;

import com.sdr.guestbook.service.base.GuestbookEntryServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author devsky
 */
@Component(
	property = {
		"json.web.service.context.name=sdr",
		"json.web.service.context.path=GuestbookEntry"
	},
	service = AopService.class
)
public class GuestbookEntryServiceImpl extends GuestbookEntryServiceBaseImpl {
}