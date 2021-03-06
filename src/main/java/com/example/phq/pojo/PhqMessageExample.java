package com.example.phq.pojo;

import java.util.ArrayList;
import java.util.List;

public class PhqMessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PhqMessageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andQuesTplIdIsNull() {
            addCriterion("ques_tpl_id is null");
            return (Criteria) this;
        }

        public Criteria andQuesTplIdIsNotNull() {
            addCriterion("ques_tpl_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuesTplIdEqualTo(Integer value) {
            addCriterion("ques_tpl_id =", value, "quesTplId");
            return (Criteria) this;
        }

        public Criteria andQuesTplIdNotEqualTo(Integer value) {
            addCriterion("ques_tpl_id <>", value, "quesTplId");
            return (Criteria) this;
        }

        public Criteria andQuesTplIdGreaterThan(Integer value) {
            addCriterion("ques_tpl_id >", value, "quesTplId");
            return (Criteria) this;
        }

        public Criteria andQuesTplIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ques_tpl_id >=", value, "quesTplId");
            return (Criteria) this;
        }

        public Criteria andQuesTplIdLessThan(Integer value) {
            addCriterion("ques_tpl_id <", value, "quesTplId");
            return (Criteria) this;
        }

        public Criteria andQuesTplIdLessThanOrEqualTo(Integer value) {
            addCriterion("ques_tpl_id <=", value, "quesTplId");
            return (Criteria) this;
        }

        public Criteria andQuesTplIdIn(List<Integer> values) {
            addCriterion("ques_tpl_id in", values, "quesTplId");
            return (Criteria) this;
        }

        public Criteria andQuesTplIdNotIn(List<Integer> values) {
            addCriterion("ques_tpl_id not in", values, "quesTplId");
            return (Criteria) this;
        }

        public Criteria andQuesTplIdBetween(Integer value1, Integer value2) {
            addCriterion("ques_tpl_id between", value1, value2, "quesTplId");
            return (Criteria) this;
        }

        public Criteria andQuesTplIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ques_tpl_id not between", value1, value2, "quesTplId");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andIsOccupyIsNull() {
            addCriterion("is_occupy is null");
            return (Criteria) this;
        }

        public Criteria andIsOccupyIsNotNull() {
            addCriterion("is_occupy is not null");
            return (Criteria) this;
        }

        public Criteria andIsOccupyEqualTo(Integer value) {
            addCriterion("is_occupy =", value, "isOccupy");
            return (Criteria) this;
        }

        public Criteria andIsOccupyNotEqualTo(Integer value) {
            addCriterion("is_occupy <>", value, "isOccupy");
            return (Criteria) this;
        }

        public Criteria andIsOccupyGreaterThan(Integer value) {
            addCriterion("is_occupy >", value, "isOccupy");
            return (Criteria) this;
        }

        public Criteria andIsOccupyGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_occupy >=", value, "isOccupy");
            return (Criteria) this;
        }

        public Criteria andIsOccupyLessThan(Integer value) {
            addCriterion("is_occupy <", value, "isOccupy");
            return (Criteria) this;
        }

        public Criteria andIsOccupyLessThanOrEqualTo(Integer value) {
            addCriterion("is_occupy <=", value, "isOccupy");
            return (Criteria) this;
        }

        public Criteria andIsOccupyIn(List<Integer> values) {
            addCriterion("is_occupy in", values, "isOccupy");
            return (Criteria) this;
        }

        public Criteria andIsOccupyNotIn(List<Integer> values) {
            addCriterion("is_occupy not in", values, "isOccupy");
            return (Criteria) this;
        }

        public Criteria andIsOccupyBetween(Integer value1, Integer value2) {
            addCriterion("is_occupy between", value1, value2, "isOccupy");
            return (Criteria) this;
        }

        public Criteria andIsOccupyNotBetween(Integer value1, Integer value2) {
            addCriterion("is_occupy not between", value1, value2, "isOccupy");
            return (Criteria) this;
        }

        public Criteria andAtCreateIsNull() {
            addCriterion("at_create is null");
            return (Criteria) this;
        }

        public Criteria andAtCreateIsNotNull() {
            addCriterion("at_create is not null");
            return (Criteria) this;
        }

        public Criteria andAtCreateEqualTo(Integer value) {
            addCriterion("at_create =", value, "atCreate");
            return (Criteria) this;
        }

        public Criteria andAtCreateNotEqualTo(Integer value) {
            addCriterion("at_create <>", value, "atCreate");
            return (Criteria) this;
        }

        public Criteria andAtCreateGreaterThan(Integer value) {
            addCriterion("at_create >", value, "atCreate");
            return (Criteria) this;
        }

        public Criteria andAtCreateGreaterThanOrEqualTo(Integer value) {
            addCriterion("at_create >=", value, "atCreate");
            return (Criteria) this;
        }

        public Criteria andAtCreateLessThan(Integer value) {
            addCriterion("at_create <", value, "atCreate");
            return (Criteria) this;
        }

        public Criteria andAtCreateLessThanOrEqualTo(Integer value) {
            addCriterion("at_create <=", value, "atCreate");
            return (Criteria) this;
        }

        public Criteria andAtCreateIn(List<Integer> values) {
            addCriterion("at_create in", values, "atCreate");
            return (Criteria) this;
        }

        public Criteria andAtCreateNotIn(List<Integer> values) {
            addCriterion("at_create not in", values, "atCreate");
            return (Criteria) this;
        }

        public Criteria andAtCreateBetween(Integer value1, Integer value2) {
            addCriterion("at_create between", value1, value2, "atCreate");
            return (Criteria) this;
        }

        public Criteria andAtCreateNotBetween(Integer value1, Integer value2) {
            addCriterion("at_create not between", value1, value2, "atCreate");
            return (Criteria) this;
        }

        public Criteria andAtUpdateIsNull() {
            addCriterion("at_update is null");
            return (Criteria) this;
        }

        public Criteria andAtUpdateIsNotNull() {
            addCriterion("at_update is not null");
            return (Criteria) this;
        }

        public Criteria andAtUpdateEqualTo(Integer value) {
            addCriterion("at_update =", value, "atUpdate");
            return (Criteria) this;
        }

        public Criteria andAtUpdateNotEqualTo(Integer value) {
            addCriterion("at_update <>", value, "atUpdate");
            return (Criteria) this;
        }

        public Criteria andAtUpdateGreaterThan(Integer value) {
            addCriterion("at_update >", value, "atUpdate");
            return (Criteria) this;
        }

        public Criteria andAtUpdateGreaterThanOrEqualTo(Integer value) {
            addCriterion("at_update >=", value, "atUpdate");
            return (Criteria) this;
        }

        public Criteria andAtUpdateLessThan(Integer value) {
            addCriterion("at_update <", value, "atUpdate");
            return (Criteria) this;
        }

        public Criteria andAtUpdateLessThanOrEqualTo(Integer value) {
            addCriterion("at_update <=", value, "atUpdate");
            return (Criteria) this;
        }

        public Criteria andAtUpdateIn(List<Integer> values) {
            addCriterion("at_update in", values, "atUpdate");
            return (Criteria) this;
        }

        public Criteria andAtUpdateNotIn(List<Integer> values) {
            addCriterion("at_update not in", values, "atUpdate");
            return (Criteria) this;
        }

        public Criteria andAtUpdateBetween(Integer value1, Integer value2) {
            addCriterion("at_update between", value1, value2, "atUpdate");
            return (Criteria) this;
        }

        public Criteria andAtUpdateNotBetween(Integer value1, Integer value2) {
            addCriterion("at_update not between", value1, value2, "atUpdate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}