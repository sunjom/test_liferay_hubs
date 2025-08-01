/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.sdr.guestbook.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Guestbook service. Represents a row in the &quot;sdr_Guestbook&quot; database table, with each column mapped to a property of this class.
 *
 * @author devsky
 * @see GuestbookModel
 * @generated
 */
@ImplementationClassName("com.sdr.guestbook.model.impl.GuestbookImpl")
@ProviderType
public interface Guestbook extends GuestbookModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.sdr.guestbook.model.impl.GuestbookImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Guestbook, Long> GUESTBOOK_ID_ACCESSOR =
		new Accessor<Guestbook, Long>() {

			@Override
			public Long get(Guestbook guestbook) {
				return guestbook.getGuestbookId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Guestbook> getTypeClass() {
				return Guestbook.class;
			}

		};

}