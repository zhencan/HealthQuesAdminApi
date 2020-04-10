package com.example.phq.pojo;

import java.util.ArrayList;
import java.util.List;

public class PhqQuestionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PhqQuestionExample() {
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

        public Criteria andQuestionIsNull() {
            addCriterion("question is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIsNotNull() {
            addCriterion("question is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionEqualTo(String value) {
            addCriterion("question =", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotEqualTo(String value) {
            addCriterion("question <>", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionGreaterThan(String value) {
            addCriterion("question >", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionGreaterThanOrEqualTo(String value) {
            addCriterion("question >=", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLessThan(String value) {
            addCriterion("question <", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLessThanOrEqualTo(String value) {
            addCriterion("question <=", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLike(String value) {
            addCriterion("question like", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotLike(String value) {
            addCriterion("question not like", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionIn(List<String> values) {
            addCriterion("question in", values, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotIn(List<String> values) {
            addCriterion("question not in", values, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionBetween(String value1, String value2) {
            addCriterion("question between", value1, value2, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotBetween(String value1, String value2) {
            addCriterion("question not between", value1, value2, "question");
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