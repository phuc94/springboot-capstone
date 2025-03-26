import { useForm, Create } from "@refinedev/antd";

import { Form, Input } from "antd";

export const CreateNoPlayer= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical">
        <Form.Item label="No. Player" name="no_player">
          <Input />
        </Form.Item>
      </Form>
    </Create>
  );
};

