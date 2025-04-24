import { useForm, useSelect, Edit } from "@refinedev/antd";

import { Form, Input, Select, } from "antd";

export const EditPlatform= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  const {selectProps} = useSelect({
    resource: 'platform',
    optionValue: 'id',
    optionLabel: 'title',
    filters: [
      {
        field: "isOrphan",
        operator: "eq",
        value: true,
      },
    ],
  })

  return (
    <Edit saveButtonProps={saveButtonProps} >
      <Form {...formProps} layout="vertical">
        <Form.Item label="Name" name="name">
          <Input />
        </Form.Item>
        <Form.Item label="Title" name="title">
          <Input />
        </Form.Item>
        <Form.Item label="Parent" name={["parent", "id"]}>
          <Select
            {...selectProps}
            defaultValue={formProps?.initialValues?.parent?.id}
          />
        </Form.Item>
      </Form>
    </Edit>
  );
};

