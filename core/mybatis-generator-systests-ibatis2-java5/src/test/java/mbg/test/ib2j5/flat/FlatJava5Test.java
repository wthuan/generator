/**
 *    Copyright 2006-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package mbg.test.ib2j5.flat;

import static mbg.test.common.util.TestUtilities.blobsAreEqual;
import static mbg.test.common.util.TestUtilities.datesAreEqual;
import static mbg.test.common.util.TestUtilities.generateRandomBlob;
import static mbg.test.common.util.TestUtilities.timesAreEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mbg.test.ib2j5.generated.flat.dao.AwfulTableDAO;
import mbg.test.ib2j5.generated.flat.dao.FieldsblobsDAO;
import mbg.test.ib2j5.generated.flat.dao.FieldsonlyDAO;
import mbg.test.ib2j5.generated.flat.dao.PkblobsDAO;
import mbg.test.ib2j5.generated.flat.dao.PkfieldsDAO;
import mbg.test.ib2j5.generated.flat.dao.PkfieldsblobsDAO;
import mbg.test.ib2j5.generated.flat.dao.PkonlyDAO;
import mbg.test.ib2j5.generated.flat.model.AwfulTable;
import mbg.test.ib2j5.generated.flat.model.AwfulTableExample;
import mbg.test.ib2j5.generated.flat.model.Fieldsblobs;
import mbg.test.ib2j5.generated.flat.model.FieldsblobsExample;
import mbg.test.ib2j5.generated.flat.model.Fieldsonly;
import mbg.test.ib2j5.generated.flat.model.FieldsonlyExample;
import mbg.test.ib2j5.generated.flat.model.Pkblobs;
import mbg.test.ib2j5.generated.flat.model.PkblobsExample;
import mbg.test.ib2j5.generated.flat.model.Pkfields;
import mbg.test.ib2j5.generated.flat.model.PkfieldsExample;
import mbg.test.ib2j5.generated.flat.model.Pkfieldsblobs;
import mbg.test.ib2j5.generated.flat.model.PkfieldsblobsExample;
import mbg.test.ib2j5.generated.flat.model.Pkonly;
import mbg.test.ib2j5.generated.flat.model.PkonlyExample;

import org.junit.Test;

/**
 * @author Jeff Butler
 * 
 */
public class FlatJava5Test extends AbstractFlatJava5Test {

    @Test
    public void testFieldsOnlyInsert() {
        FieldsonlyDAO dao = getFieldsonlyDAO();

        try {
            Fieldsonly record = new Fieldsonly();
            record.setDoublefield(11.22);
            record.setFloatfield(33.44);
            record.setIntegerfield(5);
            dao.insert(record);

            FieldsonlyExample example = new FieldsonlyExample();
            example.createCriteria().andIntegerfieldEqualTo(5);

            List<Fieldsonly> answer = dao.selectByExample(example);
            assertEquals(1, answer.size());

            Fieldsonly returnedRecord = answer.get(0);
            assertEquals(record.getIntegerfield(), returnedRecord
                    .getIntegerfield());
            assertEquals(record.getDoublefield(), returnedRecord
                    .getDoublefield());
            assertEquals(record.getFloatfield(), returnedRecord.getFloatfield());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsOnlySelectByExample() {
        FieldsonlyDAO dao = getFieldsonlyDAO();

        try {
            Fieldsonly record = new Fieldsonly();
            record.setDoublefield(11.22);
            record.setFloatfield(33.44);
            record.setIntegerfield(5);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(44.55);
            record.setFloatfield(66.77);
            record.setIntegerfield(8);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(88.99);
            record.setFloatfield(100.111);
            record.setIntegerfield(9);
            dao.insert(record);

            FieldsonlyExample example = new FieldsonlyExample();
            example.createCriteria().andIntegerfieldGreaterThan(5);

            List<Fieldsonly> answer = dao.selectByExample(example);
            assertEquals(2, answer.size());

            example = new FieldsonlyExample();
            answer = dao.selectByExample(example);
            assertEquals(3, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsOnlySelectByExampleDistinct() {
        FieldsonlyDAO dao = getFieldsonlyDAO();

        try {
            Fieldsonly record = new Fieldsonly();
            record.setDoublefield(11.22);
            record.setFloatfield(33.44);
            record.setIntegerfield(5);
            dao.insert(record);
            dao.insert(record);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(44.55);
            record.setFloatfield(66.77);
            record.setIntegerfield(8);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(88.99);
            record.setFloatfield(100.111);
            record.setIntegerfield(9);
            dao.insert(record);

            FieldsonlyExample example = new FieldsonlyExample();
            example.createCriteria().andIntegerfieldEqualTo(5);
            example.setDistinct(true);

            List<Fieldsonly> answer = dao.selectByExample(example);
            assertEquals(1, answer.size());

            example.clear();
            answer = dao.selectByExample(example);
            assertEquals(5, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testFieldsOnlySelectByExampleNoCriteria() {
        FieldsonlyDAO dao = getFieldsonlyDAO();

        try {
            Fieldsonly record = new Fieldsonly();
            record.setDoublefield(11.22);
            record.setFloatfield(33.44);
            record.setIntegerfield(5);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(44.55);
            record.setFloatfield(66.77);
            record.setIntegerfield(8);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(88.99);
            record.setFloatfield(100.111);
            record.setIntegerfield(9);
            dao.insert(record);

            FieldsonlyExample example = new FieldsonlyExample();
            example.createCriteria();

            List<Fieldsonly> answer = dao.selectByExample(example);
            assertEquals(3, answer.size());

            answer = dao.selectByExample(null);
            assertEquals(3, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsOnlyDeleteByExample() {
        FieldsonlyDAO dao = getFieldsonlyDAO();

        try {
            Fieldsonly record = new Fieldsonly();
            record.setDoublefield(11.22);
            record.setFloatfield(33.44);
            record.setIntegerfield(5);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(44.55);
            record.setFloatfield(66.77);
            record.setIntegerfield(8);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(88.99);
            record.setFloatfield(100.111);
            record.setIntegerfield(9);
            dao.insert(record);

            FieldsonlyExample example = new FieldsonlyExample();
            example.createCriteria().andIntegerfieldGreaterThan(5);

            int rows = dao.deleteByExample(example);
            assertEquals(2, rows);

            example = new FieldsonlyExample();
            List<Fieldsonly> answer = dao.selectByExample(example);
            assertEquals(1, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsOnlyCountByExample() {
        FieldsonlyDAO dao = getFieldsonlyDAO();

        try {
            Fieldsonly record = new Fieldsonly();
            record.setDoublefield(11.22);
            record.setFloatfield(33.44);
            record.setIntegerfield(5);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(44.55);
            record.setFloatfield(66.77);
            record.setIntegerfield(8);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(88.99);
            record.setFloatfield(100.111);
            record.setIntegerfield(9);
            dao.insert(record);

            FieldsonlyExample example = new FieldsonlyExample();
            example.createCriteria().andIntegerfieldGreaterThan(5);
            long rows = dao.countByExample(example);
            assertEquals(2, rows);

            example.clear();
            rows = dao.countByExample(example);
            assertEquals(3, rows);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKOnlyInsert() {
        PkonlyDAO dao = getPkonlyDAO();

        try {
            Pkonly key = new Pkonly();
            key.setId(1);
            key.setSeqNum(3);
            dao.insert(key);

            PkonlyExample example = new PkonlyExample();
            List<Pkonly> answer = dao.selectByExample(example);
            assertEquals(1, answer.size());

            Pkonly returnedRecord = answer.get(0);
            assertEquals(key.getId(), returnedRecord.getId());
            assertEquals(key.getSeqNum(), returnedRecord.getSeqNum());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKOnlyDeleteByPrimaryKey() {
        PkonlyDAO dao = getPkonlyDAO();

        try {
            Pkonly key = new Pkonly();
            key.setId(1);
            key.setSeqNum(3);
            dao.insert(key);

            key = new Pkonly();
            key.setId(5);
            key.setSeqNum(6);
            dao.insert(key);

            PkonlyExample example = new PkonlyExample();
            List<Pkonly> answer = dao.selectByExample(example);
            assertEquals(2, answer.size());

            int rows = dao.deleteByPrimaryKey(5, 6);
            assertEquals(1, rows);

            answer = dao.selectByExample(example);
            assertEquals(1, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKOnlyDeleteByExample() {
        PkonlyDAO dao = getPkonlyDAO();

        try {
            Pkonly key = new Pkonly();
            key.setId(1);
            key.setSeqNum(3);
            dao.insert(key);

            key = new Pkonly();
            key.setId(5);
            key.setSeqNum(6);
            dao.insert(key);

            key = new Pkonly();
            key.setId(7);
            key.setSeqNum(8);
            dao.insert(key);

            PkonlyExample example = new PkonlyExample();
            example.createCriteria().andIdGreaterThan(4);
            int rows = dao.deleteByExample(example);
            assertEquals(2, rows);

            example = new PkonlyExample();
            List<Pkonly> answer = dao.selectByExample(example);
            assertEquals(1, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKOnlySelectByExample() {
        PkonlyDAO dao = getPkonlyDAO();

        try {
            Pkonly key = new Pkonly();
            key.setId(1);
            key.setSeqNum(3);
            dao.insert(key);

            key = new Pkonly();
            key.setId(5);
            key.setSeqNum(6);
            dao.insert(key);

            key = new Pkonly();
            key.setId(7);
            key.setSeqNum(8);
            dao.insert(key);

            PkonlyExample example = new PkonlyExample();
            example.createCriteria().andIdGreaterThan(4);
            List<Pkonly> answer = dao.selectByExample(example);
            assertEquals(2, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKOnlySelectByExampleNoCriteria() {
        PkonlyDAO dao = getPkonlyDAO();

        try {
            Pkonly key = new Pkonly();
            key.setId(1);
            key.setSeqNum(3);
            dao.insert(key);

            key = new Pkonly();
            key.setId(5);
            key.setSeqNum(6);
            dao.insert(key);

            key = new Pkonly();
            key.setId(7);
            key.setSeqNum(8);
            dao.insert(key);

            PkonlyExample example = new PkonlyExample();
            example.createCriteria();
            List<Pkonly> answer = dao.selectByExample(example);
            assertEquals(3, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKOnlyCountByExample() {
        PkonlyDAO dao = getPkonlyDAO();

        try {
            Pkonly key = new Pkonly();
            key.setId(1);
            key.setSeqNum(3);
            dao.insert(key);

            key = new Pkonly();
            key.setId(5);
            key.setSeqNum(6);
            dao.insert(key);

            key = new Pkonly();
            key.setId(7);
            key.setSeqNum(8);
            dao.insert(key);

            PkonlyExample example = new PkonlyExample();
            example.createCriteria().andIdGreaterThan(4);
            long rows = dao.countByExample(example);
            assertEquals(2, rows);

            example.clear();
            rows = dao.countByExample(example);
            assertEquals(3, rows);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsInsert() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setDatefield(new Date());
            record.setDecimal100field(10L);
            record.setDecimal155field(new BigDecimal("15.12345"));
            record.setDecimal30field((short) 3);
            record.setDecimal60field(6);
            record.setFirstname("Jeff");
            record.setId1(1);
            record.setId2(2);
            record.setLastname("Butler");
            record.setTimefield(new Date());
            record.setTimestampfield(new Date());
            record.setStringboolean(true);

            dao.insert(record);

            Pkfields returnedRecord = dao.selectByPrimaryKey(2, 1);
            assertNotNull(returnedRecord);

            assertTrue(datesAreEqual(record.getDatefield(), returnedRecord
                    .getDatefield()));
            assertEquals(record.getDecimal100field(), returnedRecord
                    .getDecimal100field());
            assertEquals(record.getDecimal155field(), returnedRecord
                    .getDecimal155field());
            assertEquals(record.getDecimal30field(), returnedRecord
                    .getDecimal30field());
            assertEquals(record.getDecimal60field(), returnedRecord
                    .getDecimal60field());
            assertEquals(record.getFirstname(), returnedRecord.getFirstname());
            assertEquals(record.getId1(), returnedRecord.getId1());
            assertEquals(record.getId2(), returnedRecord.getId2());
            assertEquals(record.getLastname(), returnedRecord.getLastname());
            assertTrue(timesAreEqual(record.getTimefield(), returnedRecord
                    .getTimefield()));
            assertEquals(record.getTimestampfield(), returnedRecord
                    .getTimestampfield());
            assertEquals(record.isStringboolean(), returnedRecord.isStringboolean());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsUpdateByPrimaryKey() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setId1(1);
            record.setId2(2);

            dao.insert(record);

            record.setFirstname("Scott");
            record.setLastname("Jones");

            int rows = dao.updateByPrimaryKey(record);
            assertEquals(1, rows);

            Pkfields record2 = dao.selectByPrimaryKey(2, 1);

            assertEquals(record.getFirstname(), record2.getFirstname());
            assertEquals(record.getLastname(), record2.getLastname());
            assertEquals(record.getId1(), record2.getId1());
            assertEquals(record.getId2(), record2.getId2());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsUpdateByPrimaryKeySelective() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setDecimal60field(5);
            record.setId1(1);
            record.setId2(2);

            dao.insert(record);

            Pkfields newRecord = new Pkfields();
            newRecord.setId1(1);
            newRecord.setId2(2);
            newRecord.setFirstname("Scott");
            newRecord.setDecimal60field(4);

            int rows = dao.updateByPrimaryKeySelective(newRecord);
            assertEquals(1, rows);

            Pkfields returnedRecord = dao.selectByPrimaryKey(2, 1);

            assertTrue(datesAreEqual(record.getDatefield(), returnedRecord
                    .getDatefield()));
            assertEquals(record.getDecimal100field(), returnedRecord
                    .getDecimal100field());
            assertEquals(record.getDecimal155field(), returnedRecord
                    .getDecimal155field());
            assertEquals(record.getDecimal30field(), returnedRecord
                    .getDecimal30field());
            assertEquals(newRecord.getDecimal60field(), returnedRecord
                    .getDecimal60field());
            assertEquals(newRecord.getFirstname(), returnedRecord
                    .getFirstname());
            assertEquals(record.getId1(), returnedRecord.getId1());
            assertEquals(record.getId2(), returnedRecord.getId2());
            assertEquals(record.getLastname(), returnedRecord.getLastname());
            assertTrue(timesAreEqual(record.getTimefield(), returnedRecord
                    .getTimefield()));
            assertEquals(record.getTimestampfield(), returnedRecord
                    .getTimestampfield());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKfieldsDeleteByPrimaryKey() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setId1(1);
            record.setId2(2);

            dao.insert(record);

            int rows = dao.deleteByPrimaryKey(2, 1);
            assertEquals(1, rows);

            PkfieldsExample example = new PkfieldsExample();
            List<Pkfields> answer = dao.selectByExample(example);
            assertEquals(0, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsDeleteByExample() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setId1(1);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Bob");
            record.setLastname("Jones");
            record.setId1(3);
            record.setId2(4);

            dao.insert(record);

            PkfieldsExample example = new PkfieldsExample();
            List<Pkfields> answer = dao.selectByExample(example);
            assertEquals(2, answer.size());

            example = new PkfieldsExample();
            example.createCriteria().andLastnameLike("J%");
            int rows = dao.deleteByExample(example);
            assertEquals(1, rows);

            example = new PkfieldsExample();
            answer = dao.selectByExample(example);
            assertEquals(1, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsSelectByPrimaryKey() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setId1(1);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Bob");
            record.setLastname("Jones");
            record.setId1(3);
            record.setId2(4);
            dao.insert(record);

            Pkfields newRecord = dao.selectByPrimaryKey(4, 3);

            assertNotNull(newRecord);
            assertEquals(record.getFirstname(), newRecord.getFirstname());
            assertEquals(record.getLastname(), newRecord.getLastname());
            assertEquals(record.getId1(), newRecord.getId1());
            assertEquals(record.getId2(), newRecord.getId2());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsSelectByExampleLike() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(1);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(3);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(1);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(3);
            dao.insert(record);

            PkfieldsExample example = new PkfieldsExample();
            example.createCriteria().andFirstnameLike("B%");
            example.setOrderByClause("ID1, ID2");
            List<Pkfields> answer = dao.selectByExample(example);
            assertEquals(3, answer.size());
            Pkfields returnedRecord = answer.get(0);
            assertEquals(2, returnedRecord.getId1().intValue());
            assertEquals(1, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(1);
            assertEquals(2, returnedRecord.getId1().intValue());
            assertEquals(2, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(2);
            assertEquals(2, returnedRecord.getId1().intValue());
            assertEquals(3, returnedRecord.getId2().intValue());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsSelectByExampleNotLike() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(1);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(3);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(1);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(3);
            dao.insert(record);

            PkfieldsExample example = new PkfieldsExample();
            example.createCriteria().andFirstnameNotLike("B%");
            example.setOrderByClause("ID1, ID2");
            List<Pkfields> answer = dao.selectByExample(example);
            assertEquals(3, answer.size());
            Pkfields returnedRecord = answer.get(0);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(1, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(1);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(2, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(2);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(3, returnedRecord.getId2().intValue());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsSelectByExampleComplexLike() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(1);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(3);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(1);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(3);
            dao.insert(record);

            PkfieldsExample example = new PkfieldsExample();
            example.createCriteria().andFirstnameLike("B%").andId2EqualTo(3);
            example.or(example.createCriteria().andFirstnameLike("Wi%"));

            example.setOrderByClause("ID1, ID2");
            List<Pkfields> answer = dao.selectByExample(example);
            assertEquals(2, answer.size());
            Pkfields returnedRecord = answer.get(0);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(2, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(1);
            assertEquals(2, returnedRecord.getId1().intValue());
            assertEquals(3, returnedRecord.getId2().intValue());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsSelectByExampleIn() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(1);
            record.setStringboolean(true);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(3);
            record.setStringboolean(true);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(1);
            record.setStringboolean(true);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(3);
            record.setStringboolean(true);
            dao.insert(record);

            List<Integer> ids = new ArrayList<Integer>();
            ids.add(1);
            ids.add(3);
            
            List<Boolean> bools = new ArrayList<Boolean>();
            bools.add(Boolean.TRUE);

            PkfieldsExample example = new PkfieldsExample();
            example.createCriteria().andId2In(ids).andStringbooleanIn(bools);

            example.setOrderByClause("ID1, ID2");
            List<Pkfields> answer = dao.selectByExample(example);
            assertEquals(4, answer.size());
            Pkfields returnedRecord = answer.get(0);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(1, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(1);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(3, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(2);
            assertEquals(2, returnedRecord.getId1().intValue());
            assertEquals(1, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(3);
            assertEquals(2, returnedRecord.getId1().intValue());
            assertEquals(3, returnedRecord.getId2().intValue());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsSelectByExampleBetween() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(1);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(3);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(1);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(3);
            dao.insert(record);

            PkfieldsExample example = new PkfieldsExample();
            example.createCriteria().andId2Between(1, 3);

            example.setOrderByClause("ID1, ID2");
            List<Pkfields> answer = dao.selectByExample(example);
            assertEquals(6, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsSelectByExampleNoCriteria() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(1);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(3);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(1);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(3);
            dao.insert(record);

            PkfieldsExample example = new PkfieldsExample();
            example.createCriteria();

            example.setOrderByClause("ID1, ID2");
            List<Pkfields> answer = dao.selectByExample(example);
            assertEquals(6, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsSelectByExampleEscapedFields() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(1);
            record.setWierdField(11);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(2);
            record.setWierdField(22);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(1);
            record.setId2(3);
            record.setWierdField(33);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(1);
            record.setWierdField(44);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(2);
            record.setWierdField(55);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(2);
            record.setId2(3);
            record.setWierdField(66);
            dao.insert(record);

            List<Integer> values = new ArrayList<Integer>();
            values.add(11);
            values.add(22);

            PkfieldsExample example = new PkfieldsExample();
            example.createCriteria().andWierdFieldLessThan(40).andWierdFieldIn(
                    values);

            example.setOrderByClause("ID1, ID2");
            List<Pkfields> answer = dao.selectByExample(example);
            assertEquals(2, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsCountByExample() {
        PkfieldsDAO dao = getPkfieldsDAO();

        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setId1(1);
            record.setId2(2);
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Bob");
            record.setLastname("Jones");
            record.setId1(3);
            record.setId2(4);
            dao.insert(record);

            PkfieldsExample example = new PkfieldsExample();
            example.createCriteria().andLastnameLike("J%");
            long rows = dao.countByExample(example);
            assertEquals(1, rows);

            example.clear();
            rows = dao.countByExample(example);
            assertEquals(2, rows);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsInsert() {
        PkblobsDAO dao = getPkblobsDAO();

        try {
            Pkblobs record = new Pkblobs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            PkblobsExample example = new PkblobsExample();
            List<Pkblobs> answer = dao.selectByExampleWithBLOBs(example);
            assertEquals(1, answer.size());

            Pkblobs returnedRecord = answer.get(0);
            assertEquals(record.getId(), returnedRecord.getId());
            assertTrue(blobsAreEqual(record.getBlob1(), returnedRecord
                    .getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(), returnedRecord
                    .getBlob2()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsUpdateByPrimaryKeyWithBLOBs() {
        PkblobsDAO dao = getPkblobsDAO();

        try {
            Pkblobs record = new Pkblobs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            record = new Pkblobs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            int rows = dao.updateByPrimaryKey(record);
            assertEquals(1, rows);

            Pkblobs newRecord = dao.selectByPrimaryKey(3);

            assertNotNull(newRecord);
            assertEquals(record.getId(), newRecord.getId());
            assertTrue(blobsAreEqual(record.getBlob1(), newRecord.getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(), newRecord.getBlob2()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsUpdateByPrimaryKeySelective() {
        PkblobsDAO dao = getPkblobsDAO();

        try {
            Pkblobs record = new Pkblobs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            Pkblobs newRecord = new Pkblobs();
            newRecord.setId(3);
            newRecord.setBlob2(generateRandomBlob());
            dao.updateByPrimaryKeySelective(newRecord);

            Pkblobs returnedRecord = dao.selectByPrimaryKey(3);
            assertNotNull(returnedRecord);
            assertEquals(record.getId(), returnedRecord.getId());
            assertTrue(blobsAreEqual(record.getBlob1(), returnedRecord
                    .getBlob1()));
            assertTrue(blobsAreEqual(newRecord.getBlob2(), returnedRecord
                    .getBlob2()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsDeleteByPrimaryKey() {
        PkblobsDAO dao = getPkblobsDAO();

        try {
            Pkblobs record = new Pkblobs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            PkblobsExample example = new PkblobsExample();
            List<Pkblobs> answer = dao.selectByExampleWithoutBLOBs(example);
            assertEquals(1, answer.size());

            int rows = dao.deleteByPrimaryKey(3);
            assertEquals(1, rows);

            example = new PkblobsExample();
            answer = dao.selectByExampleWithoutBLOBs(example);
            assertEquals(0, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsDeleteByExample() {
        PkblobsDAO dao = getPkblobsDAO();

        try {
            Pkblobs record = new Pkblobs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            record = new Pkblobs();
            record.setId(6);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            PkblobsExample example = new PkblobsExample();
            List<Pkblobs> answer = dao.selectByExampleWithoutBLOBs(example);
            assertEquals(2, answer.size());

            example = new PkblobsExample();
            example.createCriteria().andIdLessThan(4);
            int rows = dao.deleteByExample(example);
            assertEquals(1, rows);

            example = new PkblobsExample();
            answer = dao.selectByExampleWithoutBLOBs(example);
            assertEquals(1, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsSelectByPrimaryKey() {
        PkblobsDAO dao = getPkblobsDAO();

        try {
            Pkblobs record = new Pkblobs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            record = new Pkblobs();
            record.setId(6);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            Pkblobs newRecord = dao.selectByPrimaryKey(6);
            assertNotNull(newRecord);
            assertEquals(record.getId(), newRecord.getId());
            assertTrue(blobsAreEqual(record.getBlob1(), newRecord.getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(), newRecord.getBlob2()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsSelectByExampleWithoutBlobs() {
        PkblobsDAO dao = getPkblobsDAO();

        try {
            Pkblobs record = new Pkblobs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            record = new Pkblobs();
            record.setId(6);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            PkblobsExample example = new PkblobsExample();
            example.createCriteria().andIdGreaterThan(4);
            List<Pkblobs> answer = dao.selectByExampleWithoutBLOBs(example);

            assertEquals(1, answer.size());

            Pkblobs key = answer.get(0);
            assertEquals(6, key.getId().intValue());
            assertNull(key.getBlob1());
            assertNull(key.getBlob2());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsSelectByExampleWithoutBlobsNoCriteria() {
        PkblobsDAO dao = getPkblobsDAO();

        try {
            Pkblobs record = new Pkblobs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            record = new Pkblobs();
            record.setId(6);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            PkblobsExample example = new PkblobsExample();
            example.createCriteria();
            List<Pkblobs> answer = dao.selectByExampleWithoutBLOBs(example);

            assertEquals(2, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsSelectByExampleWithBlobs() {
        PkblobsDAO dao = getPkblobsDAO();

        try {
            Pkblobs record = new Pkblobs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            record = new Pkblobs();
            record.setId(6);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            PkblobsExample example = new PkblobsExample();
            example.createCriteria().andIdGreaterThan(4);
            List<Pkblobs> answer = dao.selectByExampleWithBLOBs(example);

            assertEquals(1, answer.size());

            Pkblobs newRecord = answer.get(0);
            assertEquals(record.getId(), newRecord.getId());
            assertTrue(blobsAreEqual(record.getBlob1(), newRecord.getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(), newRecord.getBlob2()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsCountByExample() {
        PkblobsDAO dao = getPkblobsDAO();

        try {
            Pkblobs record = new Pkblobs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            record = new Pkblobs();
            record.setId(6);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            PkblobsExample example = new PkblobsExample();
            example.createCriteria().andIdLessThan(4);
            long rows = dao.countByExample(example);
            assertEquals(1, rows);

            example.clear();
            rows = dao.countByExample(example);
            assertEquals(2, rows);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsInsert() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();

        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            PkfieldsblobsExample example = new PkfieldsblobsExample();
            List<Pkfieldsblobs> answer = dao.selectByExampleWithBLOBs(example);
            assertEquals(1, answer.size());

            Pkfieldsblobs returnedRecord = answer.get(0);
            assertEquals(record.getId1(), returnedRecord.getId1());
            assertEquals(record.getId2(), returnedRecord.getId2());
            assertEquals(record.getFirstname(), returnedRecord.getFirstname());
            assertEquals(record.getLastname(), returnedRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(), returnedRecord
                    .getBlob1()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsUpdateByPrimaryKeyWithBLOBs() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();

        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            Pkfieldsblobs updateRecord = new Pkfieldsblobs();
            updateRecord.setId1(3);
            updateRecord.setId2(4);
            updateRecord.setFirstname("Scott");
            updateRecord.setLastname("Jones");
            updateRecord.setBlob1(generateRandomBlob());

            int rows = dao.updateByPrimaryKeyWithBLOBs(updateRecord);
            assertEquals(1, rows);

            Pkfieldsblobs newRecord = dao.selectByPrimaryKey(3, 4);
            assertEquals(updateRecord.getFirstname(), newRecord.getFirstname());
            assertEquals(updateRecord.getLastname(), newRecord.getLastname());
            assertEquals(record.getId1(), newRecord.getId1());
            assertEquals(record.getId2(), newRecord.getId2());
            assertTrue(blobsAreEqual(updateRecord.getBlob1(), newRecord
                    .getBlob1()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsUpdateByPrimaryKeyWithoutBLOBs() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();

        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            Pkfieldsblobs updateRecord = new Pkfieldsblobs();
            updateRecord.setId1(3);
            updateRecord.setId2(4);
            updateRecord.setFirstname("Scott");
            updateRecord.setLastname("Jones");

            int rows = dao.updateByPrimaryKeyWithoutBLOBs(updateRecord);
            assertEquals(1, rows);

            Pkfieldsblobs newRecord = dao.selectByPrimaryKey(3, 4);
            assertEquals(updateRecord.getFirstname(), newRecord.getFirstname());
            assertEquals(updateRecord.getLastname(), newRecord.getLastname());
            assertEquals(record.getId1(), newRecord.getId1());
            assertEquals(record.getId2(), newRecord.getId2());
            assertTrue(blobsAreEqual(record.getBlob1(), newRecord.getBlob1()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsUpdateByPrimaryKeySelective() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();

        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            Pkfieldsblobs updateRecord = new Pkfieldsblobs();
            updateRecord.setId1(3);
            updateRecord.setId2(4);
            updateRecord.setLastname("Jones");

            int rows = dao.updateByPrimaryKeySelective(updateRecord);
            assertEquals(1, rows);

            Pkfieldsblobs returnedRecord = dao.selectByPrimaryKey(3, 4);
            assertEquals(record.getFirstname(), returnedRecord.getFirstname());
            assertEquals(updateRecord.getLastname(), returnedRecord
                    .getLastname());
            assertEquals(record.getId1(), returnedRecord.getId1());
            assertEquals(record.getId2(), returnedRecord.getId2());
            assertTrue(blobsAreEqual(record.getBlob1(), returnedRecord
                    .getBlob1()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsDeleteByPrimaryKey() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();

        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            record = new Pkfieldsblobs();
            record.setId1(5);
            record.setId2(6);
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            PkfieldsblobsExample example = new PkfieldsblobsExample();
            List<Pkfieldsblobs> answer = dao
                    .selectByExampleWithoutBLOBs(example);
            assertEquals(2, answer.size());

            int rows = dao.deleteByPrimaryKey(5, 6);
            assertEquals(1, rows);

            example = new PkfieldsblobsExample();
            answer = dao.selectByExampleWithoutBLOBs(example);
            assertEquals(1, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsDeleteByExample() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();

        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            record = new Pkfieldsblobs();
            record.setId1(5);
            record.setId2(6);
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            PkfieldsblobsExample example = new PkfieldsblobsExample();
            List<Pkfieldsblobs> answer = dao
                    .selectByExampleWithoutBLOBs(example);
            assertEquals(2, answer.size());

            example = new PkfieldsblobsExample();
            example.createCriteria().andId1NotEqualTo(3);
            int rows = dao.deleteByExample(example);
            assertEquals(1, rows);

            example = new PkfieldsblobsExample();
            answer = dao.selectByExampleWithoutBLOBs(example);
            assertEquals(1, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsSelectByPrimaryKey() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();

        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            record = new Pkfieldsblobs();
            record.setId1(5);
            record.setId2(6);
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            PkfieldsblobsExample example = new PkfieldsblobsExample();
            List<Pkfieldsblobs> answer = dao
                    .selectByExampleWithoutBLOBs(example);
            assertEquals(2, answer.size());

            Pkfieldsblobs newRecord = dao.selectByPrimaryKey(5, 6);
            assertEquals(record.getId1(), newRecord.getId1());
            assertEquals(record.getId2(), newRecord.getId2());
            assertEquals(record.getFirstname(), newRecord.getFirstname());
            assertEquals(record.getLastname(), newRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(), newRecord.getBlob1()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsSelectByExampleWithoutBlobs() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();

        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            record = new Pkfieldsblobs();
            record.setId1(5);
            record.setId2(6);
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            PkfieldsblobsExample example = new PkfieldsblobsExample();
            example.createCriteria().andId2EqualTo(6);
            List<Pkfieldsblobs> answer = dao
                    .selectByExampleWithoutBLOBs(example);
            assertEquals(1, answer.size());

            Pkfieldsblobs newRecord = answer.get(0);
            assertEquals(record.getId1(), newRecord.getId1());
            assertEquals(record.getId2(), newRecord.getId2());
            assertEquals(record.getFirstname(), newRecord.getFirstname());
            assertEquals(record.getLastname(), newRecord.getLastname());
            assertNull(newRecord.getBlob1());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsSelectByExampleWithBlobs() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();

        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            record = new Pkfieldsblobs();
            record.setId1(5);
            record.setId2(6);
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            PkfieldsblobsExample example = new PkfieldsblobsExample();
            example.createCriteria().andId2EqualTo(6);
            List<Pkfieldsblobs> answer = dao.selectByExampleWithBLOBs(example);
            assertEquals(1, answer.size());

            Pkfieldsblobs newRecord = answer.get(0);
            assertEquals(record.getId1(), newRecord.getId1());
            assertEquals(record.getId2(), newRecord.getId2());
            assertEquals(record.getFirstname(), newRecord.getFirstname());
            assertEquals(record.getLastname(), newRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(), newRecord.getBlob1()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsSelectByExampleWithBlobsNoCriteria() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();

        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            record = new Pkfieldsblobs();
            record.setId1(5);
            record.setId2(6);
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            PkfieldsblobsExample example = new PkfieldsblobsExample();
            example.createCriteria();
            List<Pkfieldsblobs> answer = dao.selectByExampleWithBLOBs(example);
            assertEquals(2, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsBlobsInsert() {
        FieldsblobsDAO dao = getFieldsblobsDAO();

        try {
            Fieldsblobs record = new Fieldsblobs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            FieldsblobsExample example = new FieldsblobsExample();
            List<Fieldsblobs> answer = dao.selectByExampleWithBLOBs(example);
            assertEquals(1, answer.size());

            Fieldsblobs returnedRecord = answer.get(0);
            assertEquals(record.getFirstname(), returnedRecord.getFirstname());
            assertEquals(record.getLastname(), returnedRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(), returnedRecord
                    .getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(), returnedRecord
                    .getBlob2()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsBlobsDeleteByExample() {
        FieldsblobsDAO dao = getFieldsblobsDAO();

        try {
            Fieldsblobs record = new Fieldsblobs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            record = new Fieldsblobs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            FieldsblobsExample example = new FieldsblobsExample();
            List<Fieldsblobs> answer = dao.selectByExampleWithoutBLOBs(example);
            assertEquals(2, answer.size());

            example = new FieldsblobsExample();
            example.createCriteria().andFirstnameLike("S%");
            int rows = dao.deleteByExample(example);
            assertEquals(1, rows);

            example = new FieldsblobsExample();
            answer = dao.selectByExampleWithoutBLOBs(example);
            assertEquals(1, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsBlobsSelectByExampleWithoutBlobs() {
        FieldsblobsDAO dao = getFieldsblobsDAO();

        try {
            Fieldsblobs record = new Fieldsblobs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            record = new Fieldsblobs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            FieldsblobsExample example = new FieldsblobsExample();
            example.createCriteria().andFirstnameLike("S%");
            List<Fieldsblobs> answer = dao.selectByExampleWithoutBLOBs(example);
            assertEquals(1, answer.size());

            Fieldsblobs newRecord = answer.get(0);
            assertEquals(record.getFirstname(), newRecord.getFirstname());
            assertEquals(record.getLastname(), newRecord.getLastname());
            assertNull(newRecord.getBlob1());
            assertNull(newRecord.getBlob2());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsBlobsSelectByExampleWithBlobs() {
        FieldsblobsDAO dao = getFieldsblobsDAO();

        try {
            Fieldsblobs record = new Fieldsblobs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            record = new Fieldsblobs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            FieldsblobsExample example = new FieldsblobsExample();
            example.createCriteria().andFirstnameLike("S%");
            List<Fieldsblobs> answer = dao.selectByExampleWithBLOBs(example);
            assertEquals(1, answer.size());

            Fieldsblobs newRecord = answer.get(0);
            assertEquals(record.getFirstname(), newRecord.getFirstname());
            assertEquals(record.getLastname(), newRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(), newRecord.getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(), newRecord.getBlob2()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsBlobsSelectByExampleWithBlobsNoCriteria() {
        FieldsblobsDAO dao = getFieldsblobsDAO();

        try {
            Fieldsblobs record = new Fieldsblobs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            record = new Fieldsblobs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            FieldsblobsExample example = new FieldsblobsExample();
            example.createCriteria();
            List<Fieldsblobs> answer = dao.selectByExampleWithBLOBs(example);
            assertEquals(2, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsCountByExample() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();

        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            record = new Pkfieldsblobs();
            record.setId1(5);
            record.setId2(6);
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            PkfieldsblobsExample example = new PkfieldsblobsExample();
            example.createCriteria().andId1NotEqualTo(3);
            long rows = dao.countByExample(example);
            assertEquals(1, rows);

            example.clear();
            rows = dao.countByExample(example);
            assertEquals(2, rows);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableInsert() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();

            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");

            Integer generatedCustomerId = dao.insert(record);
            assertEquals(57, generatedCustomerId.intValue());

            AwfulTable returnedRecord = dao
                    .selectByPrimaryKey(generatedCustomerId);

            assertEquals(generatedCustomerId, returnedRecord.getCustomerId());
            assertEquals(record.geteMail(), returnedRecord.geteMail());
            assertEquals(record.getEmailaddress(), returnedRecord
                    .getEmailaddress());
            assertEquals(record.getFirstFirstName(), returnedRecord
                    .getFirstFirstName());
            assertEquals(record.getFrom(), returnedRecord.getFrom());
            assertEquals(record.getId1(), returnedRecord.getId1());
            assertEquals(record.getId2(), returnedRecord.getId2());
            assertEquals(record.getId5(), returnedRecord.getId5());
            assertEquals(record.getId6(), returnedRecord.getId6());
            assertEquals(record.getId7(), returnedRecord.getId7());
            assertEquals(record.getSecondFirstName(), returnedRecord
                    .getSecondFirstName());
            assertEquals(record.getThirdFirstName(), returnedRecord
                    .getThirdFirstName());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableInsertSelective() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();

            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");

            Integer generatedCustomerId = dao.insertSelective(record);
            assertEquals(57, generatedCustomerId.intValue());

            AwfulTable returnedRecord = dao
                    .selectByPrimaryKey(generatedCustomerId);

            assertEquals(generatedCustomerId, returnedRecord.getCustomerId());
            assertEquals(record.geteMail(), returnedRecord.geteMail());
            assertEquals(record.getEmailaddress(), returnedRecord
                    .getEmailaddress());
            assertEquals("Mabel", returnedRecord.getFirstFirstName());
            assertEquals(record.getFrom(), returnedRecord.getFrom());
            assertEquals(record.getId1(), returnedRecord.getId1());
            assertEquals(record.getId2(), returnedRecord.getId2());
            assertEquals(record.getId5(), returnedRecord.getId5());
            assertEquals(record.getId6(), returnedRecord.getId6());
            assertEquals(record.getId7(), returnedRecord.getId7());
            assertEquals(record.getSecondFirstName(), returnedRecord
                    .getSecondFirstName());
            assertEquals(record.getThirdFirstName(), returnedRecord
                    .getThirdFirstName());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testAwfulTableUpdateByPrimaryKey() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");

            Integer generatedCustomerId = dao.insert(record);

            record.setId1(11);
            record.setId2(22);

            int rows = dao.updateByPrimaryKey(record);
            assertEquals(1, rows);

            AwfulTable returnedRecord = dao.selectByPrimaryKey(generatedCustomerId);

            assertEquals(generatedCustomerId, returnedRecord.getCustomerId());
            assertEquals(record.geteMail(), returnedRecord.geteMail());
            assertEquals(record.getEmailaddress(), returnedRecord
                    .getEmailaddress());
            assertEquals(record.getFirstFirstName(), returnedRecord
                    .getFirstFirstName());
            assertEquals(record.getFrom(), returnedRecord.getFrom());
            assertEquals(record.getId1(), returnedRecord.getId1());
            assertEquals(record.getId2(), returnedRecord.getId2());
            assertEquals(record.getId5(), returnedRecord.getId5());
            assertEquals(record.getId6(), returnedRecord.getId6());
            assertEquals(record.getId7(), returnedRecord.getId7());
            assertEquals(record.getSecondFirstName(), returnedRecord
                    .getSecondFirstName());
            assertEquals(record.getThirdFirstName(), returnedRecord
                    .getThirdFirstName());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableUpdateByPrimaryKeySelective() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");

            Integer generatedCustomerId = dao.insert(record);

            AwfulTable newRecord = new AwfulTable();
            newRecord.setCustomerId(generatedCustomerId);
            newRecord.setId1(11);
            newRecord.setId2(22);

            int rows = dao.updateByPrimaryKeySelective(newRecord);
            assertEquals(1, rows);

            AwfulTable returnedRecord = dao.selectByPrimaryKey(generatedCustomerId);

            assertEquals(generatedCustomerId, returnedRecord.getCustomerId());
            assertEquals(record.geteMail(), returnedRecord.geteMail());
            assertEquals(record.getEmailaddress(), returnedRecord
                    .getEmailaddress());
            assertEquals(record.getFirstFirstName(), returnedRecord
                    .getFirstFirstName());
            assertEquals(record.getFrom(), returnedRecord.getFrom());
            assertEquals(newRecord.getId1(), returnedRecord.getId1());
            assertEquals(newRecord.getId2(), returnedRecord.getId2());
            assertEquals(record.getId5(), returnedRecord.getId5());
            assertEquals(record.getId6(), returnedRecord.getId6());
            assertEquals(record.getId7(), returnedRecord.getId7());
            assertEquals(record.getSecondFirstName(), returnedRecord
                    .getSecondFirstName());
            assertEquals(record.getThirdFirstName(), returnedRecord
                    .getThirdFirstName());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableDeleteByPrimaryKey() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");

            Integer generatedCustomerId = dao.insert(record);

            int rows = dao.deleteByPrimaryKey(generatedCustomerId);
            assertEquals(1, rows);

            AwfulTableExample example = new AwfulTableExample();
            List<AwfulTable> answer = dao.selectByExample(example);
            assertEquals(0, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableDeleteByExample() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");

            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("fred2@fred.com");
            record.setEmailaddress("alsofred2@fred.com");
            record.setFirstFirstName("fred11");
            record.setFrom("from from field");
            record.setId1(11);
            record.setId2(22);
            record.setId5(55);
            record.setId6(66);
            record.setId7(77);
            record.setSecondFirstName("fred22");
            record.setThirdFirstName("fred33");

            dao.insert(record);

            AwfulTableExample example = new AwfulTableExample();
            List<AwfulTable> answer = dao.selectByExample(example);
            assertEquals(2, answer.size());

            example = new AwfulTableExample();
            example.createCriteria().andEMailLike("fred@%");
            int rows = dao.deleteByExample(example);
            assertEquals(1, rows);

            example.clear();
            answer = dao.selectByExample(example);
            assertEquals(1, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableSelectByPrimaryKey() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");

            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("fred2@fred.com");
            record.setEmailaddress("alsofred2@fred.com");
            record.setFirstFirstName("fred11");
            record.setFrom("from from field");
            record.setId1(11);
            record.setId2(22);
            record.setId5(55);
            record.setId6(66);
            record.setId7(77);
            record.setSecondFirstName("fred22");
            record.setThirdFirstName("fred33");

            Integer generatedKey = dao.insert(record);

            AwfulTable returnedRecord = dao.selectByPrimaryKey(generatedKey);

            assertNotNull(returnedRecord);
            assertEquals(record.getCustomerId(), returnedRecord.getCustomerId());
            assertEquals(record.geteMail(), returnedRecord.geteMail());
            assertEquals(record.getEmailaddress(), returnedRecord
                    .getEmailaddress());
            assertEquals(record.getFirstFirstName(), returnedRecord
                    .getFirstFirstName());
            assertEquals(record.getFrom(), returnedRecord.getFrom());
            assertEquals(record.getId1(), returnedRecord.getId1());
            assertEquals(record.getId2(), returnedRecord.getId2());
            assertEquals(record.getId5(), returnedRecord.getId5());
            assertEquals(record.getId6(), returnedRecord.getId6());
            assertEquals(record.getId7(), returnedRecord.getId7());
            assertEquals(record.getSecondFirstName(), returnedRecord
                    .getSecondFirstName());
            assertEquals(record.getThirdFirstName(), returnedRecord
                    .getThirdFirstName());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableSelectByExampleLike() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("wilma@wilma.com");
            record.setEmailaddress("alsoWilma@wilma.com");
            record.setFirstFirstName("wilma1");
            record.setFrom("from field");
            record.setId1(11);
            record.setId2(22);
            record.setId5(55);
            record.setId6(66);
            record.setId7(77);
            record.setSecondFirstName("wilma2");
            record.setThirdFirstName("wilma3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("pebbles@pebbles.com");
            record.setEmailaddress("alsoPebbles@pebbles.com");
            record.setFirstFirstName("pebbles1");
            record.setFrom("from field");
            record.setId1(111);
            record.setId2(222);
            record.setId5(555);
            record.setId6(666);
            record.setId7(777);
            record.setSecondFirstName("pebbles2");
            record.setThirdFirstName("pebbles3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("barney@barney.com");
            record.setEmailaddress("alsoBarney@barney.com");
            record.setFirstFirstName("barney1");
            record.setFrom("from field");
            record.setId1(1111);
            record.setId2(2222);
            record.setId5(5555);
            record.setId6(6666);
            record.setId7(7777);
            record.setSecondFirstName("barney2");
            record.setThirdFirstName("barney3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("betty@betty.com");
            record.setEmailaddress("alsoBetty@betty.com");
            record.setFirstFirstName("betty1");
            record.setFrom("from field");
            record.setId1(11111);
            record.setId2(22222);
            record.setId5(55555);
            record.setId6(66666);
            record.setId7(77777);
            record.setSecondFirstName("betty2");
            record.setThirdFirstName("betty3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("bammbamm@bammbamm.com");
            record.setEmailaddress("alsoBammbamm@bammbamm.com");
            record.setFirstFirstName("bammbamm1");
            record.setFrom("from field");
            record.setId1(111111);
            record.setId2(222222);
            record.setId5(555555);
            record.setId6(666666);
            record.setId7(777777);
            record.setSecondFirstName("bammbamm2");
            record.setThirdFirstName("bammbamm3");
            dao.insert(record);

            AwfulTableExample example = new AwfulTableExample();
            example.createCriteria().andFirstFirstNameLike("b%");
            example.setOrderByClause("\"A_CuStOmEr iD\"");
            List<AwfulTable> answer = dao.selectByExample(example);
            assertEquals(3, answer.size());
            AwfulTable returnedRecord = answer.get(0);
            assertEquals(1111, returnedRecord.getId1().intValue());
            assertEquals(2222, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(1);
            assertEquals(11111, returnedRecord.getId1().intValue());
            assertEquals(22222, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(2);
            assertEquals(111111, returnedRecord.getId1().intValue());
            assertEquals(222222, returnedRecord.getId2().intValue());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableSelectByExampleNotLike() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("wilma@wilma.com");
            record.setEmailaddress("alsoWilma@wilma.com");
            record.setFirstFirstName("wilma1");
            record.setFrom("from field");
            record.setId1(11);
            record.setId2(22);
            record.setId5(55);
            record.setId6(66);
            record.setId7(77);
            record.setSecondFirstName("wilma2");
            record.setThirdFirstName("wilma3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("pebbles@pebbles.com");
            record.setEmailaddress("alsoPebbles@pebbles.com");
            record.setFirstFirstName("pebbles1");
            record.setFrom("from field");
            record.setId1(111);
            record.setId2(222);
            record.setId5(555);
            record.setId6(666);
            record.setId7(777);
            record.setSecondFirstName("pebbles2");
            record.setThirdFirstName("pebbles3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("barney@barney.com");
            record.setEmailaddress("alsoBarney@barney.com");
            record.setFirstFirstName("barney1");
            record.setFrom("from field");
            record.setId1(1111);
            record.setId2(2222);
            record.setId5(5555);
            record.setId6(6666);
            record.setId7(7777);
            record.setSecondFirstName("barney2");
            record.setThirdFirstName("barney3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("betty@betty.com");
            record.setEmailaddress("alsoBetty@betty.com");
            record.setFirstFirstName("betty1");
            record.setFrom("from field");
            record.setId1(11111);
            record.setId2(22222);
            record.setId5(55555);
            record.setId6(66666);
            record.setId7(77777);
            record.setSecondFirstName("betty2");
            record.setThirdFirstName("betty3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("bammbamm@bammbamm.com");
            record.setEmailaddress("alsoBammbamm@bammbamm.com");
            record.setFirstFirstName("bammbamm1");
            record.setFrom("from field");
            record.setId1(111111);
            record.setId2(222222);
            record.setId5(555555);
            record.setId6(666666);
            record.setId7(777777);
            record.setSecondFirstName("bammbamm2");
            record.setThirdFirstName("bammbamm3");
            dao.insert(record);

            AwfulTableExample example = new AwfulTableExample();
            example.createCriteria().andFirstFirstNameNotLike("b%");
            example.setOrderByClause("\"A_CuStOmEr iD\"");
            List<AwfulTable> answer = dao.selectByExample(example);
            assertEquals(3, answer.size());
            AwfulTable returnedRecord = answer.get(0);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(2, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(1);
            assertEquals(11, returnedRecord.getId1().intValue());
            assertEquals(22, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(2);
            assertEquals(111, returnedRecord.getId1().intValue());
            assertEquals(222, returnedRecord.getId2().intValue());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testAwfulTableSelectByExampleComplexLike() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("wilma@wilma.com");
            record.setEmailaddress("alsoWilma@wilma.com");
            record.setFirstFirstName("wilma1");
            record.setFrom("from field");
            record.setId1(11);
            record.setId2(22);
            record.setId5(55);
            record.setId6(66);
            record.setId7(77);
            record.setSecondFirstName("wilma2");
            record.setThirdFirstName("wilma3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("pebbles@pebbles.com");
            record.setEmailaddress("alsoPebbles@pebbles.com");
            record.setFirstFirstName("pebbles1");
            record.setFrom("from field");
            record.setId1(111);
            record.setId2(222);
            record.setId5(555);
            record.setId6(666);
            record.setId7(777);
            record.setSecondFirstName("pebbles2");
            record.setThirdFirstName("pebbles3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("barney@barney.com");
            record.setEmailaddress("alsoBarney@barney.com");
            record.setFirstFirstName("barney1");
            record.setFrom("from field");
            record.setId1(1111);
            record.setId2(2222);
            record.setId5(5555);
            record.setId6(6666);
            record.setId7(7777);
            record.setSecondFirstName("barney2");
            record.setThirdFirstName("barney3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("betty@betty.com");
            record.setEmailaddress("alsoBetty@betty.com");
            record.setFirstFirstName("betty1");
            record.setFrom("from field");
            record.setId1(11111);
            record.setId2(22222);
            record.setId5(55555);
            record.setId6(66666);
            record.setId7(77777);
            record.setSecondFirstName("betty2");
            record.setThirdFirstName("betty3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("bammbamm@bammbamm.com");
            record.setEmailaddress("alsoBammbamm@bammbamm.com");
            record.setFirstFirstName("bammbamm1");
            record.setFrom("from field");
            record.setId1(111111);
            record.setId2(222222);
            record.setId5(555555);
            record.setId6(666666);
            record.setId7(777777);
            record.setSecondFirstName("bammbamm2");
            record.setThirdFirstName("bammbamm3");
            dao.insert(record);

            AwfulTableExample example = new AwfulTableExample();
            example.createCriteria().andFirstFirstNameLike("b%").andId2EqualTo(222222);
            example.or(example.createCriteria().andFirstFirstNameLike("wi%"));
            example.setOrderByClause("\"A_CuStOmEr iD\"");
            List<AwfulTable> answer = dao.selectByExample(example);
            assertEquals(2, answer.size());
            AwfulTable returnedRecord = answer.get(0);
            assertEquals(11, returnedRecord.getId1().intValue());
            assertEquals(22, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(1);
            assertEquals(111111, returnedRecord.getId1().intValue());
            assertEquals(222222, returnedRecord.getId2().intValue());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableSelectByExampleIn() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("wilma@wilma.com");
            record.setEmailaddress("alsoWilma@wilma.com");
            record.setFirstFirstName("wilma1");
            record.setFrom("from field");
            record.setId1(11);
            record.setId2(22);
            record.setId5(55);
            record.setId6(66);
            record.setId7(77);
            record.setSecondFirstName("wilma2");
            record.setThirdFirstName("wilma3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("pebbles@pebbles.com");
            record.setEmailaddress("alsoPebbles@pebbles.com");
            record.setFirstFirstName("pebbles1");
            record.setFrom("from field");
            record.setId1(111);
            record.setId2(222);
            record.setId5(555);
            record.setId6(666);
            record.setId7(777);
            record.setSecondFirstName("pebbles2");
            record.setThirdFirstName("pebbles3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("barney@barney.com");
            record.setEmailaddress("alsoBarney@barney.com");
            record.setFirstFirstName("barney1");
            record.setFrom("from field");
            record.setId1(1111);
            record.setId2(2222);
            record.setId5(5555);
            record.setId6(6666);
            record.setId7(7777);
            record.setSecondFirstName("barney2");
            record.setThirdFirstName("barney3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("betty@betty.com");
            record.setEmailaddress("alsoBetty@betty.com");
            record.setFirstFirstName("betty1");
            record.setFrom("from field");
            record.setId1(11111);
            record.setId2(22222);
            record.setId5(55555);
            record.setId6(66666);
            record.setId7(77777);
            record.setSecondFirstName("betty2");
            record.setThirdFirstName("betty3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("bammbamm@bammbamm.com");
            record.setEmailaddress("alsoBammbamm@bammbamm.com");
            record.setFirstFirstName("bammbamm1");
            record.setFrom("from field");
            record.setId1(111111);
            record.setId2(222222);
            record.setId5(555555);
            record.setId6(666666);
            record.setId7(777777);
            record.setSecondFirstName("bammbamm2");
            record.setThirdFirstName("bammbamm3");
            dao.insert(record);

            List<Integer> ids = new ArrayList<Integer>();
            ids.add(1);
            ids.add(11);

            AwfulTableExample example = new AwfulTableExample();
            example.createCriteria().andId1In(ids);
            example.setOrderByClause("\"A_CuStOmEr iD\"");
            List<AwfulTable> answer = dao.selectByExample(example);
            
            assertEquals(2, answer.size());
            AwfulTable returnedRecord = answer.get(0);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(2, returnedRecord.getId2().intValue());
            returnedRecord = answer.get(1);
            assertEquals(11, returnedRecord.getId1().intValue());
            assertEquals(22, returnedRecord.getId2().intValue());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableSelectByExampleBetween() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("wilma@wilma.com");
            record.setEmailaddress("alsoWilma@wilma.com");
            record.setFirstFirstName("wilma1");
            record.setFrom("from field");
            record.setId1(11);
            record.setId2(22);
            record.setId5(55);
            record.setId6(66);
            record.setId7(77);
            record.setSecondFirstName("wilma2");
            record.setThirdFirstName("wilma3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("pebbles@pebbles.com");
            record.setEmailaddress("alsoPebbles@pebbles.com");
            record.setFirstFirstName("pebbles1");
            record.setFrom("from field");
            record.setId1(111);
            record.setId2(222);
            record.setId5(555);
            record.setId6(666);
            record.setId7(777);
            record.setSecondFirstName("pebbles2");
            record.setThirdFirstName("pebbles3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("barney@barney.com");
            record.setEmailaddress("alsoBarney@barney.com");
            record.setFirstFirstName("barney1");
            record.setFrom("from field");
            record.setId1(1111);
            record.setId2(2222);
            record.setId5(5555);
            record.setId6(6666);
            record.setId7(7777);
            record.setSecondFirstName("barney2");
            record.setThirdFirstName("barney3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("betty@betty.com");
            record.setEmailaddress("alsoBetty@betty.com");
            record.setFirstFirstName("betty1");
            record.setFrom("from field");
            record.setId1(11111);
            record.setId2(22222);
            record.setId5(55555);
            record.setId6(66666);
            record.setId7(77777);
            record.setSecondFirstName("betty2");
            record.setThirdFirstName("betty3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("bammbamm@bammbamm.com");
            record.setEmailaddress("alsoBammbamm@bammbamm.com");
            record.setFirstFirstName("bammbamm1");
            record.setFrom("from field");
            record.setId1(111111);
            record.setId2(222222);
            record.setId5(555555);
            record.setId6(666666);
            record.setId7(777777);
            record.setSecondFirstName("bammbamm2");
            record.setThirdFirstName("bammbamm3");
            dao.insert(record);

            AwfulTableExample example = new AwfulTableExample();
            example.createCriteria().andId1Between(1, 1000);
            List<AwfulTable> answer = dao.selectByExample(example);
            assertEquals(3, answer.size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableSelectByExampleNoCriteria() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("wilma@wilma.com");
            record.setEmailaddress("alsoWilma@wilma.com");
            record.setFirstFirstName("wilma1");
            record.setFrom("from field");
            record.setId1(11);
            record.setId2(22);
            record.setId5(55);
            record.setId6(66);
            record.setId7(77);
            record.setSecondFirstName("wilma2");
            record.setThirdFirstName("wilma3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("pebbles@pebbles.com");
            record.setEmailaddress("alsoPebbles@pebbles.com");
            record.setFirstFirstName("pebbles1");
            record.setFrom("from field");
            record.setId1(111);
            record.setId2(222);
            record.setId5(555);
            record.setId6(666);
            record.setId7(777);
            record.setSecondFirstName("pebbles2");
            record.setThirdFirstName("pebbles3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("barney@barney.com");
            record.setEmailaddress("alsoBarney@barney.com");
            record.setFirstFirstName("barney1");
            record.setFrom("from field");
            record.setId1(1111);
            record.setId2(2222);
            record.setId5(5555);
            record.setId6(6666);
            record.setId7(7777);
            record.setSecondFirstName("barney2");
            record.setThirdFirstName("barney3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("betty@betty.com");
            record.setEmailaddress("alsoBetty@betty.com");
            record.setFirstFirstName("betty1");
            record.setFrom("from field");
            record.setId1(11111);
            record.setId2(22222);
            record.setId5(55555);
            record.setId6(66666);
            record.setId7(77777);
            record.setSecondFirstName("betty2");
            record.setThirdFirstName("betty3");
            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("bammbamm@bammbamm.com");
            record.setEmailaddress("alsoBammbamm@bammbamm.com");
            record.setFirstFirstName("bammbamm1");
            record.setFrom("from field");
            record.setId1(111111);
            record.setId2(222222);
            record.setId5(555555);
            record.setId6(666666);
            record.setId7(777777);
            record.setSecondFirstName("bammbamm2");
            record.setThirdFirstName("bammbamm3");
            dao.insert(record);

            AwfulTableExample example = new AwfulTableExample();
            example.createCriteria();
            example.setOrderByClause("\"A_CuStOmEr iD\" desc");
            
            List<AwfulTable> answer = dao.selectByExample(example);
            assertEquals(6, answer.size());
            AwfulTable returnedRecord = answer.get(0);
            assertEquals(111111, returnedRecord.getId1().intValue());
            returnedRecord = answer.get(1);
            assertEquals(11111, returnedRecord.getId1().intValue());
            returnedRecord = answer.get(2);
            assertEquals(1111, returnedRecord.getId1().intValue());
            returnedRecord = answer.get(3);
            assertEquals(111, returnedRecord.getId1().intValue());
            returnedRecord = answer.get(4);
            assertEquals(11, returnedRecord.getId1().intValue());
            returnedRecord = answer.get(5);
            assertEquals(1, returnedRecord.getId1().intValue());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableCountByExample() {
        AwfulTableDAO dao = getAwfulTableDAO();

        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");

            dao.insert(record);

            record = new AwfulTable();
            record.seteMail("fred2@fred.com");
            record.setEmailaddress("alsofred2@fred.com");
            record.setFirstFirstName("fred11");
            record.setFrom("from from field");
            record.setId1(11);
            record.setId2(22);
            record.setId5(55);
            record.setId6(66);
            record.setId7(77);
            record.setSecondFirstName("fred22");
            record.setThirdFirstName("fred33");

            dao.insert(record);

            AwfulTableExample example = new AwfulTableExample();
            example.createCriteria().andEMailLike("fred@%");
            long rows = dao.countByExample(example);
            assertEquals(1, rows);

            example.clear();
            rows = dao.countByExample(example);
            assertEquals(2, rows);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testEquals1() {
        Pkfields pkfields1 = new Pkfields();
        assertFalse(pkfields1.equals(null));
    }
    
    @Test
    public void testEquals2() {
        Pkfields pkfields1 = new Pkfields();
        Pkfields pkfields2 = new Pkfields();
        assertTrue(pkfields1.equals(pkfields2));
    }
    
    @Test
    public void testEquals3() {
        Pkfields pkfields1 = new Pkfields();
        pkfields1.setId1(2);
        
        Pkfields pkfields2 = new Pkfields();
        pkfields2.setId1(2);
        
        assertTrue(pkfields1.equals(pkfields2));
    }
    
    @Test
    public void testEquals4() {
        Pkfields pkfields1 = new Pkfields();
        pkfields1.setId1(2);
        
        Pkfields pkfields2 = new Pkfields();
        pkfields2.setId1(3);
        
        assertFalse(pkfields1.equals(pkfields2));
    }

    @Test
    public void testEquals5() {
        AwfulTable awfulTable1 = new AwfulTable();
        awfulTable1.setActive(false);
        awfulTable1.setCustomerId(3);
        awfulTable1.seteMail("fred@fred.com");
        awfulTable1.setEmailaddress("fred@fred.com");
        awfulTable1.setFirstFirstName("Fred");
        awfulTable1.setFrom("from");
        awfulTable1.setId1(22);
        awfulTable1.setId2(33);
        awfulTable1.setId5(55);
        awfulTable1.setId6(66);
        awfulTable1.setId7(77);
        awfulTable1.setLastName("Rubble");
        awfulTable1.setSecondFirstName("Bamm Bamm");
        awfulTable1.setThirdFirstName("Pebbles");

        AwfulTable awfulTable2 = new AwfulTable();
        awfulTable2.setActive(false);
        awfulTable2.setCustomerId(3);
        awfulTable2.seteMail("fred@fred.com");
        awfulTable2.setEmailaddress("fred@fred.com");
        awfulTable2.setFirstFirstName("Fred");
        awfulTable2.setFrom("from");
        awfulTable2.setId1(22);
        awfulTable2.setId2(33);
        awfulTable2.setId5(55);
        awfulTable2.setId6(66);
        awfulTable2.setId7(77);
        awfulTable2.setLastName("Rubble");
        awfulTable2.setSecondFirstName("Bamm Bamm");
        awfulTable2.setThirdFirstName("Pebbles");
        
        assertTrue(awfulTable1.equals(awfulTable2));
        
        awfulTable2.setActive(true);
        assertFalse(awfulTable1.equals(awfulTable2));
    }

    @Test
    public void testHashCode1() {
        Pkfields pkfields1 = new Pkfields();
        Pkfields pkfields2 = new Pkfields();
        assertTrue(pkfields1.hashCode() == pkfields2.hashCode());
    }
    
    @Test
    public void testHashCode2() {
        Pkfields pkfields1 = new Pkfields();
        pkfields1.setId1(2);
        
        Pkfields pkfields2 = new Pkfields();
        pkfields2.setId1(2);
        
        assertTrue(pkfields1.hashCode() == pkfields2.hashCode());
    }

    @Test
    public void testHashCode3() {
        AwfulTable awfulTable1 = new AwfulTable();
        awfulTable1.setActive(false);
        awfulTable1.setCustomerId(3);
        awfulTable1.seteMail("fred@fred.com");
        awfulTable1.setEmailaddress("fred@fred.com");
        awfulTable1.setFirstFirstName("Fred");
        awfulTable1.setFrom("from");
        awfulTable1.setId1(22);
        awfulTable1.setId2(33);
        awfulTable1.setId5(55);
        awfulTable1.setId6(66);
        awfulTable1.setId7(77);
        awfulTable1.setLastName("Rubble");
        awfulTable1.setSecondFirstName("Bamm Bamm");
        awfulTable1.setThirdFirstName("Pebbles");

        AwfulTable awfulTable2 = new AwfulTable();
        awfulTable2.setActive(false);
        awfulTable2.setCustomerId(3);
        awfulTable2.seteMail("fred@fred.com");
        awfulTable2.setEmailaddress("fred@fred.com");
        awfulTable2.setFirstFirstName("Fred");
        awfulTable2.setFrom("from");
        awfulTable2.setId1(22);
        awfulTable2.setId2(33);
        awfulTable2.setId5(55);
        awfulTable2.setId6(66);
        awfulTable2.setId7(77);
        awfulTable2.setLastName("Rubble");
        awfulTable2.setSecondFirstName("Bamm Bamm");
        awfulTable2.setThirdFirstName("Pebbles");
        
        assertTrue(awfulTable1.hashCode() == awfulTable2.hashCode());
    }
}
