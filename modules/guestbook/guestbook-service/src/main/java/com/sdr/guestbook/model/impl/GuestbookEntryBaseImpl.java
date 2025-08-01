/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.sdr.guestbook.model.impl;

import com.sdr.guestbook.model.GuestbookEntry;
import com.sdr.guestbook.service.GuestbookEntryLocalServiceUtil;

/**
 * The extended model base implementation for the GuestbookEntry service. Represents a row in the &quot;sdr_GuestbookEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link GuestbookEntryImpl}.
 * </p>
 *
 * @author devsky
 * @see GuestbookEntryImpl
 * @see GuestbookEntry
 * @generated
 */
public abstract class GuestbookEntryBaseImpl
	extends GuestbookEntryModelImpl implements GuestbookEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a guestbook entry model instance should use the <code>GuestbookEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			GuestbookEntryLocalServiceUtil.addGuestbookEntry(this);
		}
		else {
			GuestbookEntryLocalServiceUtil.updateGuestbookEntry(this);
		}
	}

}