<template>
  <div>
    <common-table :pageSize="3" :headers="headers" :dataUrl="dataUrl"  @onView="onView"></common-table>
    <${nonPrefixName?lower_case}-modal ref="${nonPrefixName?lower_case}Modal"/>

  </div>
</template>

<script>
import CommonTable from '@/components/table'
import ${nonPrefixName?cap_first}Modal from '@/components/module/${nonPrefixName?lower_case}-modal'
export default {
  components: {
    CommonTable,
    ${nonPrefixName?cap_first}Modal
  },
  data () {
    return {
      headers: [
        <#list table.fields as field>
        {
          title: '${field.propertyName}',
          key: '${field.propertyName}'
        }
        </#list>
        {
          title: 'Action',
          slot: 'action',
          width: 150,
          align: 'center'
        }
      ],
      dataUrl: '${package.ModuleName}/${controllerMappingHyphen}/list'
    }
  },
  methods: {
    onView (row) {
      console.log(this.$refs)
      this.$refs.${nonPrefixName?lower_case}Modal.showModal()

    },
    ok () {
      this.$Message.info('Clicked ok')
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

<style scoped>
</style>
