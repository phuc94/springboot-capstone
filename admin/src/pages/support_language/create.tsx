import { useForm, Create } from "@refinedev/antd";

import { Form, Input } from "antd";

export const CreateSupportLanguage= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical">
        <Form.Item label="Language" name="language">
          <Input />
        </Form.Item>
      </Form>
    </Create>
  );
};

