import { useForm, Create, useSelect } from "@refinedev/antd";
import { Form, Input, Select } from "antd";

export const CreatePlatform= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  const {selectProps} = useSelect({
    resource: 'platform',
    optionLabel: 'title',
    optionValue: 'id',
    filters: [
      {
        field: "isOrphan",
        operator: "eq",
        value: true,
      },
    ],
  })

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical">
        <Form.Item label="Name" name="name">
          <Input />
        </Form.Item>
        <Form.Item label="Title" name="title">
          <Input />
        </Form.Item>
        <Form.Item label="Parent" name="parent_id">
          <Select
            {...selectProps}
          />
        </Form.Item>
      </Form>
    </Create>
  );
};

