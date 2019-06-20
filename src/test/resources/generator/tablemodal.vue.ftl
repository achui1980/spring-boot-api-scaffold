<template>
  <div>
    <Modal v-model="visible" title="View/Edit" @on-ok="ok" @on-cancel="cancel">
      <Form ref="formInline" :model="formInline">
        <#list table.fields as field>
           <FormItem prop="${field.propertyName}">
             <Input type="text" v-model="formInline.${field.propertyName}" placeholder="${field.propertyName}"></Input>
           </FormItem>
        </#list>
        <FormItem>
          <Button type="primary" @click="handleSubmit('formInline')">Signin</Button>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>
<script>
import axios from '@/libs/api.request'
import util from '@/utils/util'
export default {
  data () {
    return {
      visible: false,
      formInline: {
        <#list table.fields as field>
          ${field.propertyName}: "",
        </#list>
      }
    }
  },
  methods: {
    showModal (record) {
      Object.assign(this.formInline, record)
      this.visible = true
    },
    ok () {
      axios
        .request({
          baseURL: util.apiBasePath,
          url: `${package.ModuleName}/${controllerMappingHyphen}/update`,
          method: 'put',
          data: this.formInline
        })
        .then(res => {
           this.$Message.info('Update successfully!')
           this.$emit("onRefresh")
        })

    },
    cancel () {
      this.$Message.info('Clicked cancel')
    },
    handleSubmit (name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          this.$Message.success('Success!')
          this.modal1 = false
        } else {
          this.$Message.error('Fail!')
        }
      })
    }
  }
}
</script>


<div>
    <Button type="primary" @click="visible = true">Display dialog box</Button>
    <Modal v-model="visible" title="View/Edit" @on-ok="ok" @on-cancel="cancel">
      <Form ref="formInline" :model="formInline">
      <#list table.fields as field>
         <FormItem prop="${field.propertyName}">
          <Input type="text" v-model="formInline.${field.propertyName}" placeholder="${field.propertyName}"></Input>
        </FormItem>
      </#list>
        <FormItem>
          <Button type="primary" @click="handleSubmit('formInline')">Signin</Button>
        </FormItem>
      </Form>
    </Modal>
</div>
