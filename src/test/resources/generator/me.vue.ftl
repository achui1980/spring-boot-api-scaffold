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
