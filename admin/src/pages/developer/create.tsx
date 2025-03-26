import { useForm, Create } from "@refinedev/antd";

import { Form, Input } from "antd";

export const CreateDeveloper= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical">
        <Form.Item label="Name" name="name">
          <Input />
        </Form.Item>
      </Form>
    </Create>
  );
};

