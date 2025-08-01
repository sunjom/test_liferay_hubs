/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.sdr.guestbook.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.sdr.guestbook.exception.NoSuchGuestbookException;
import com.sdr.guestbook.model.Guestbook;
import com.sdr.guestbook.service.GuestbookLocalServiceUtil;
import com.sdr.guestbook.service.persistence.GuestbookPersistence;
import com.sdr.guestbook.service.persistence.GuestbookUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class GuestbookPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.sdr.guestbook.service"));

	@Before
	public void setUp() {
		_persistence = GuestbookUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Guestbook> iterator = _guestbooks.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Guestbook guestbook = _persistence.create(pk);

		Assert.assertNotNull(guestbook);

		Assert.assertEquals(guestbook.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Guestbook newGuestbook = addGuestbook();

		_persistence.remove(newGuestbook);

		Guestbook existingGuestbook = _persistence.fetchByPrimaryKey(
			newGuestbook.getPrimaryKey());

		Assert.assertNull(existingGuestbook);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGuestbook();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Guestbook newGuestbook = _persistence.create(pk);

		newGuestbook.setUuid(RandomTestUtil.randomString());

		newGuestbook.setName(RandomTestUtil.randomString());

		newGuestbook.setGroupId(RandomTestUtil.nextLong());

		newGuestbook.setCompanyId(RandomTestUtil.nextLong());

		newGuestbook.setUserId(RandomTestUtil.nextLong());

		newGuestbook.setUserName(RandomTestUtil.randomString());

		newGuestbook.setCreateDate(RandomTestUtil.nextDate());

		newGuestbook.setModifiedDate(RandomTestUtil.nextDate());

		newGuestbook.setField1(RandomTestUtil.randomString());

		newGuestbook.setField2(RandomTestUtil.randomBoolean());

		newGuestbook.setField3(RandomTestUtil.nextInt());

		newGuestbook.setField4(RandomTestUtil.nextDate());

		newGuestbook.setField5(RandomTestUtil.randomString());

		newGuestbook.setStatus(RandomTestUtil.nextInt());

		newGuestbook.setStatusByUserId(RandomTestUtil.nextLong());

		newGuestbook.setStatusByUserName(RandomTestUtil.randomString());

		newGuestbook.setStatusDate(RandomTestUtil.nextDate());

		_guestbooks.add(_persistence.update(newGuestbook));

		Guestbook existingGuestbook = _persistence.findByPrimaryKey(
			newGuestbook.getPrimaryKey());

		Assert.assertEquals(
			existingGuestbook.getUuid(), newGuestbook.getUuid());
		Assert.assertEquals(
			existingGuestbook.getGuestbookId(), newGuestbook.getGuestbookId());
		Assert.assertEquals(
			existingGuestbook.getName(), newGuestbook.getName());
		Assert.assertEquals(
			existingGuestbook.getGroupId(), newGuestbook.getGroupId());
		Assert.assertEquals(
			existingGuestbook.getCompanyId(), newGuestbook.getCompanyId());
		Assert.assertEquals(
			existingGuestbook.getUserId(), newGuestbook.getUserId());
		Assert.assertEquals(
			existingGuestbook.getUserName(), newGuestbook.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingGuestbook.getCreateDate()),
			Time.getShortTimestamp(newGuestbook.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingGuestbook.getModifiedDate()),
			Time.getShortTimestamp(newGuestbook.getModifiedDate()));
		Assert.assertEquals(
			existingGuestbook.getField1(), newGuestbook.getField1());
		Assert.assertEquals(
			existingGuestbook.isField2(), newGuestbook.isField2());
		Assert.assertEquals(
			existingGuestbook.getField3(), newGuestbook.getField3());
		Assert.assertEquals(
			Time.getShortTimestamp(existingGuestbook.getField4()),
			Time.getShortTimestamp(newGuestbook.getField4()));
		Assert.assertEquals(
			existingGuestbook.getField5(), newGuestbook.getField5());
		Assert.assertEquals(
			existingGuestbook.getStatus(), newGuestbook.getStatus());
		Assert.assertEquals(
			existingGuestbook.getStatusByUserId(),
			newGuestbook.getStatusByUserId());
		Assert.assertEquals(
			existingGuestbook.getStatusByUserName(),
			newGuestbook.getStatusByUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingGuestbook.getStatusDate()),
			Time.getShortTimestamp(newGuestbook.getStatusDate()));
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Guestbook newGuestbook = addGuestbook();

		Guestbook existingGuestbook = _persistence.findByPrimaryKey(
			newGuestbook.getPrimaryKey());

		Assert.assertEquals(existingGuestbook, newGuestbook);
	}

	@Test(expected = NoSuchGuestbookException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Guestbook> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"sdr_Guestbook", "uuid", true, "guestbookId", true, "name", true,
			"groupId", true, "companyId", true, "userId", true, "userName",
			true, "createDate", true, "modifiedDate", true, "field1", true,
			"field2", true, "field3", true, "field4", true, "field5", true,
			"status", true, "statusByUserId", true, "statusByUserName", true,
			"statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Guestbook newGuestbook = addGuestbook();

		Guestbook existingGuestbook = _persistence.fetchByPrimaryKey(
			newGuestbook.getPrimaryKey());

		Assert.assertEquals(existingGuestbook, newGuestbook);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Guestbook missingGuestbook = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGuestbook);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Guestbook newGuestbook1 = addGuestbook();
		Guestbook newGuestbook2 = addGuestbook();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGuestbook1.getPrimaryKey());
		primaryKeys.add(newGuestbook2.getPrimaryKey());

		Map<Serializable, Guestbook> guestbooks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, guestbooks.size());
		Assert.assertEquals(
			newGuestbook1, guestbooks.get(newGuestbook1.getPrimaryKey()));
		Assert.assertEquals(
			newGuestbook2, guestbooks.get(newGuestbook2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Guestbook> guestbooks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(guestbooks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Guestbook newGuestbook = addGuestbook();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGuestbook.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Guestbook> guestbooks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, guestbooks.size());
		Assert.assertEquals(
			newGuestbook, guestbooks.get(newGuestbook.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Guestbook> guestbooks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(guestbooks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Guestbook newGuestbook = addGuestbook();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGuestbook.getPrimaryKey());

		Map<Serializable, Guestbook> guestbooks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, guestbooks.size());
		Assert.assertEquals(
			newGuestbook, guestbooks.get(newGuestbook.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			GuestbookLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Guestbook>() {

				@Override
				public void performAction(Guestbook guestbook) {
					Assert.assertNotNull(guestbook);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Guestbook newGuestbook = addGuestbook();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Guestbook.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"guestbookId", newGuestbook.getGuestbookId()));

		List<Guestbook> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		Guestbook existingGuestbook = result.get(0);

		Assert.assertEquals(existingGuestbook, newGuestbook);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Guestbook.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"guestbookId", RandomTestUtil.nextLong()));

		List<Guestbook> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Guestbook newGuestbook = addGuestbook();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Guestbook.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("guestbookId"));

		Object newGuestbookId = newGuestbook.getGuestbookId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"guestbookId", new Object[] {newGuestbookId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingGuestbookId = result.get(0);

		Assert.assertEquals(existingGuestbookId, newGuestbookId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Guestbook.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("guestbookId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"guestbookId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Guestbook newGuestbook = addGuestbook();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newGuestbook.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		Guestbook newGuestbook = addGuestbook();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Guestbook.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"guestbookId", newGuestbook.getGuestbookId()));

		List<Guestbook> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(Guestbook guestbook) {
		Assert.assertEquals(
			guestbook.getUuid(),
			ReflectionTestUtil.invoke(
				guestbook, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "uuid_"));
		Assert.assertEquals(
			Long.valueOf(guestbook.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				guestbook, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));
	}

	protected Guestbook addGuestbook() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Guestbook guestbook = _persistence.create(pk);

		guestbook.setUuid(RandomTestUtil.randomString());

		guestbook.setName(RandomTestUtil.randomString());

		guestbook.setGroupId(RandomTestUtil.nextLong());

		guestbook.setCompanyId(RandomTestUtil.nextLong());

		guestbook.setUserId(RandomTestUtil.nextLong());

		guestbook.setUserName(RandomTestUtil.randomString());

		guestbook.setCreateDate(RandomTestUtil.nextDate());

		guestbook.setModifiedDate(RandomTestUtil.nextDate());

		guestbook.setField1(RandomTestUtil.randomString());

		guestbook.setField2(RandomTestUtil.randomBoolean());

		guestbook.setField3(RandomTestUtil.nextInt());

		guestbook.setField4(RandomTestUtil.nextDate());

		guestbook.setField5(RandomTestUtil.randomString());

		guestbook.setStatus(RandomTestUtil.nextInt());

		guestbook.setStatusByUserId(RandomTestUtil.nextLong());

		guestbook.setStatusByUserName(RandomTestUtil.randomString());

		guestbook.setStatusDate(RandomTestUtil.nextDate());

		_guestbooks.add(_persistence.update(guestbook));

		return guestbook;
	}

	private List<Guestbook> _guestbooks = new ArrayList<Guestbook>();
	private GuestbookPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}