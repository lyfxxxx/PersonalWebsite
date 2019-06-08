<template>
  <div class="hello">
    <LoginMenu></LoginMenu>
    <div class="main-container">
      <div class="educations-card">
        <h1>添加教育经历</h1>
        <div class="education-new">
          <Form ref="newEduForm" :model="newEduForm" :rules="eduRules" label-position="top">
            <FormItem label="阶段" prop="stage">
              <Input v-model="newEduForm.stage" placeholder="请输入教育阶段" />
            </FormItem>
            <FormItem label="起始年月">
              <Row>
                <Col span="12" class="colClass">
                  <FormItem prop="startYear" >
                    <Select v-model="newEduForm.startYear" placeholder="选择起始年份">
                      <Option v-for="(i, index) in years" :key="index" :value="i">{{i}}</Option>
                    </Select>
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem prop="startMonth">
                    <Select v-model="newEduForm.startMonth" placeholder="选择起始月份">
                      <Option v-for="(j, index) in months" :key="index" :value="j">{{j}}</Option>
                    </Select>
                  </FormItem>
                </Col>
              </Row>
            </FormItem>
            <FormItem label="结束年月">
              <Row>
                <Col span="12" class="colClass">
                  <FormItem prop="endYear">
                    <Select v-model="newEduForm.endYear" placeholder="选择结束年份">
                      <Option v-for="(k, index) in years" :key="index" :value="k">{{k}}</Option>
                    </Select>
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem prop="endMonth">
                    <Select v-model="newEduForm.endMonth" placeholder="选择结束月份">
                      <Option v-for="(l, index) in months" :key="index" :value="l">{{l}}</Option>
                    </Select>
                  </FormItem>
                </Col>
              </Row>
            </FormItem>
            <FormItem label="主修" prop="major">
              <Input v-model="newEduForm.major" placeholder="请输入主修专业名称" />
            </FormItem>
            <FormItem label="学校" prop="school">
              <Input v-model="newEduForm.school" placeholder="请输入学校名称" />
            </FormItem>
            <div class="button">
              <FormItem>
                <Button type="primary" @click="handleSubmit('newEduForm')">保存</Button>
                <Button type="warning" @click="handleCancel">取消</Button>
              </FormItem>
            </div>
          </Form>
        </div>
      </div>
      <MyFooter></MyFooter>
    </div>
  </div>
</template>

<script>
import LoginMenu from '../login/LoginMenu'
import MyFooter from '../common/footer'
export default {
  components: {LoginMenu, MyFooter},
  name: 'newedu',
  mounted () {
    for (let i = 2010; i < 2030; i++) {
      this.years.push(String(i))
    }
  },
  data () {
    const timeValidator = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入年份及月份！'))
      }
      if (this.newEduForm.startYear > this.newEduForm.endYear ||
      ((this.newEduForm.startYear === this.newEduForm.endYear) && (this.newEduForm.startMonth > this.newEduForm.endMonth))) {
        callback(new Error('请检查输入年月！'))
      } else {
        callback()
      }
    }

    return {
      years: [],
      months: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
      newEduForm: {
        stage: '',
        major: '',
        school: '',
        startYear: '',
        startMonth: '',
        endYear: '',
        endMonth: ''
      },
      sendEdu: {
        id: 0,
        stage: '',
        major: '',
        school: '',
        time: ''
      },
      eduRules: {
        stage: [
          {required: true, message: '请输入教育阶段', trigger: 'blur'},
          { type: 'string', max: 50, message: '请不要输入超过50字', trigger: 'blur' }
        ],
        major: [
          {required: true, message: '请输入主修专业，没有请填写无', trigger: 'blur'},
          { type: 'string', max: 50, message: '请不要输入超过50字', trigger: 'blur' }
        ],
        school: [
          {required: true, message: '请输入学校名称', trigger: 'blur'},
          { type: 'string', max: 50, message: '请不要输入超过50字', trigger: 'blur' }
        ],
        startYear: [
          {validator: timeValidator, trigger: 'change'},
          {required: true, message: '请选择起始年份', trigger: 'click'}
        ],
        startMonth: [
          {validator: timeValidator, trigger: 'change'},
          {required: true, message: '请选择起始月份', trigger: 'click'}
        ],
        endYear: [
          {validator: timeValidator, trigger: 'change'},
          {required: true, message: '请选择结束年份', trigger: 'click'}
        ],
        endMonth: [
          {validator: timeValidator, trigger: 'change'},
          {required: true, message: '请选择结束月份', trigger: 'click'}
        ]
      }
    }
  },
  methods: {
    handleCancel () {
      this.$router.push('/loginedu')
    },
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          let startTime = this.newEduForm.startYear + '/' + this.newEduForm.startMonth
          let endTime = this.newEduForm.endYear + '/' + this.newEduForm.endMonth
          this.sendEdu.time = startTime + '-' + endTime
          this.sendEdu.major = this.newEduForm.major
          this.sendEdu.school = this.newEduForm.school
          this.sendEdu.stage = this.newEduForm.stage
          this.$axios({
            url: '/insertEdu',
            method: 'post',
            data: this.sendEdu
          }).then(res => {
            if (res.data.code === 200) {
              this.$Message.success('添加成功')
              this.$router.push('/loginedu')
            } else {
              console.log(res.data.message)
              this.$Message.error('添加失败！')
            }
          }).catch(() => {
            this.$Message.error('内部错误！')
          })
        } else {
          this.$Message.error('请检查表单')
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.main-container {
  margin: 20px 0;
  width: 100%;
}

.educations-card {
  margin: 0 auto;
  position: relative;
  width: 80%;
  max-width: 100%;
  border-radius: 5px;
  border: 1px solid rgba(34,36,38,.15);
  box-shadow: 0 1px 2px 0;
  background: #fff;
}

.educations-card > h1 {
  margin: 10px;
  text-align: center;
}

.education-new {
  text-align: left;
  width: 50%;
  margin: 0 auto;
}

.education > h2 {
  text-align: left;
  margin: 10px;
  font-size: 16px;
}

#time {
  text-align: right;
  margin: 10px;
  font-size: 14px;
}

.education > p {
  font-size: 16px;
  text-align: left;
  margin: 10px;
  color: #657180;
}

.button {
  text-align: center;
}

.colClass {
  padding-right: 10px;
}

</style>
