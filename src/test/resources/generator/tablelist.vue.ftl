<template>
  <div>
    <common-table :pageSize="3" :headers="headers" :dataUrl="dataUrl"  @onView="onView" @onDelete="onDelete"></common-table>
    <${nonPrefixName?lower_case}-modal ref="${nonPrefixName?lower_case}Modal" @onRefresh="onRefresh"/>

  </div>
</template>

<script>
import axios from '@/libs/api.request'
import util from '@/utils/util'
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
      this.$refs.${nonPrefixName?lower_case}Modal.showModal(row)

    },
    onDelete (row) {
      axios
       .request({
         baseURL: util.apiBasePath,
         url: `${package.ModuleName}/${controllerMappingHyphen}/delete/${r'${row.id}'}`,
         method: 'delete'
       })
       .then(res => {
          this.$Message.info('delete successfully!')
          this.$refs.commonTable.getPageData()
       })
    }
  }
}
</script>

<style scoped>
</style>
