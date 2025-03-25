import { useForm, Create } from "@refinedev/antd";

import { Form, Input } from "antd";

export const CreateDescription= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical">
        <Form.Item label="Description" name="description">
          <Input />
        </Form.Item>
      </Form>
    </Create>
  );
};

