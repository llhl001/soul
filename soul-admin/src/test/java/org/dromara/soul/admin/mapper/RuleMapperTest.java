/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.soul.admin.mapper;

import org.dromara.soul.admin.AbstractSpringIntegrationTest;
import org.dromara.soul.admin.entity.RuleDO;
import org.dromara.soul.admin.query.RuleQuery;
import org.dromara.soul.common.utils.UUIDUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test cases for RuleMapperTest.
 *
 * @author hellboy0621
 */
public final class RuleMapperTest extends AbstractSpringIntegrationTest {

    @Resource
    private RuleMapper ruleMapper;

    @Test
    public void selectById() {
        RuleDO ruleDO = buildRuleDO();
        int insert = ruleMapper.insert(ruleDO);
        assertThat(insert, equalTo(1));

        RuleDO resultRuleDO = ruleMapper.selectById(ruleDO.getId());
        assertThat(ruleDO, equalTo(resultRuleDO));

        int delete = ruleMapper.delete(ruleDO.getId());
        assertThat(delete, equalTo(1));
    }

    @Test
    public void selectByQuery() {
        RuleDO ruleDO = buildRuleDO();
        int insert = ruleMapper.insert(ruleDO);
        assertThat(insert, equalTo(1));

        RuleQuery ruleQuery = new RuleQuery();
        ruleQuery.setSelectorId(ruleDO.getSelectorId());
        List<RuleDO> ruleDOList = ruleMapper.selectByQuery(ruleQuery);
        assertThat(ruleDOList.size(), equalTo(1));
        assertThat(ruleDO, equalTo(ruleDOList.get(0)));

        int delete = ruleMapper.delete(ruleDO.getId());
        assertThat(delete, equalTo(1));
    }

    @Test
    public void findBySelectorId() {
        RuleDO ruleDO = buildRuleDO();
        int insert = ruleMapper.insert(ruleDO);
        assertThat(insert, equalTo(1));

        List<RuleDO> ruleDOList = ruleMapper.findBySelectorId(ruleDO.getSelectorId());
        assertThat(ruleDOList.size(), equalTo(1));
        assertThat(ruleDO, equalTo(ruleDOList.get(0)));

        int delete = ruleMapper.delete(ruleDO.getId());
        assertThat(delete, equalTo(1));
    }

    @Test
    public void findByName() {
        RuleDO ruleDO = buildRuleDO();
        int insert = ruleMapper.insert(ruleDO);
        assertThat(insert, equalTo(1));

        RuleDO findByName = ruleMapper.findByName(ruleDO.getName());
        assertThat(ruleDO, equalTo(findByName));

        int delete = ruleMapper.delete(ruleDO.getId());
        assertThat(delete, equalTo(1));
    }

    @Test
    public void countByQuery() {
        RuleDO ruleDO = buildRuleDO();
        int insert = ruleMapper.insert(ruleDO);
        assertThat(insert, equalTo(1));

        RuleQuery ruleQuery = new RuleQuery();
        ruleQuery.setSelectorId(ruleDO.getSelectorId());
        Integer count = ruleMapper.countByQuery(ruleQuery);
        assertThat(count, equalTo(1));

        int delete = ruleMapper.delete(ruleDO.getId());
        assertThat(delete, equalTo(1));
    }

    @Test
    public void insert() {
        RuleDO ruleDO = buildRuleDO();
        int insert = ruleMapper.insert(ruleDO);
        assertThat(insert, equalTo(1));

        int delete = ruleMapper.delete(ruleDO.getId());
        assertThat(delete, equalTo(1));
    }

    @Test
    public void insertSelective() {
        RuleDO ruleDO = buildRuleDO();
        int insert = ruleMapper.insertSelective(ruleDO);
        assertThat(insert, equalTo(1));

        int delete = ruleMapper.delete(ruleDO.getId());
        assertThat(delete, equalTo(1));
    }

    @Test
    public void update() {
        RuleDO ruleDO = buildRuleDO();
        int insert = ruleMapper.insert(ruleDO);
        assertThat(insert, equalTo(1));

        ruleDO.setHandle("test-handle-update");
        int update = ruleMapper.update(ruleDO);
        assertThat(update, equalTo(1));

        RuleDO resultRuleDO = ruleMapper.selectById(ruleDO.getId());
        assertThat(ruleDO, equalTo(resultRuleDO));

        int delete = ruleMapper.delete(ruleDO.getId());
        assertThat(delete, equalTo(1));
    }

    @Test
    public void updateSelective() {
        RuleDO ruleDO = buildRuleDO();
        int insert = ruleMapper.insert(ruleDO);
        assertThat(insert, equalTo(1));

        ruleDO.setHandle("test-handle-updateSelective");
        int update = ruleMapper.update(ruleDO);
        assertThat(update, equalTo(1));

        RuleDO resultRuleDO = ruleMapper.selectById(ruleDO.getId());
        assertThat(ruleDO.getHandle(), equalTo(resultRuleDO.getHandle()));

        int delete = ruleMapper.delete(ruleDO.getId());
        assertThat(delete, equalTo(1));
    }

    @Test
    public void delete() {
        RuleDO ruleDO = buildRuleDO();
        int insert = ruleMapper.insert(ruleDO);
        assertThat(insert, equalTo(1));

        int delete = ruleMapper.delete(ruleDO.getId());
        assertThat(delete, equalTo(1));

        RuleDO resultRuleDO = ruleMapper.selectById(ruleDO.getId());
        assertThat(resultRuleDO, equalTo(null));
    }

    @Test
    public void selectAll() {
        RuleDO ruleDO = buildRuleDO();
        int insert = ruleMapper.insert(ruleDO);
        assertThat(insert, equalTo(1));

        List<RuleDO> ruleDOList = ruleMapper.selectAll();
        assertThat(ruleDOList.size(), equalTo(1));

        int delete = ruleMapper.delete(ruleDO.getId());
        assertThat(delete, equalTo(1));
    }

    private RuleDO buildRuleDO() {
        String id = UUIDUtils.getInstance().generateShortUuid();
        RuleDO ruleDO = new RuleDO();
        ruleDO.setId(id);
        ruleDO.setName("test-name-" + new Random().nextInt());
        ruleDO.setEnabled(true);
        ruleDO.setHandle("test-handle");
        ruleDO.setLoged(true);
        ruleDO.setMatchMode(1);
        ruleDO.setSelectorId("test-selector-1");
        ruleDO.setSort(1);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        ruleDO.setDateCreated(now);
        ruleDO.setDateUpdated(now);
        return ruleDO;
    }
}
