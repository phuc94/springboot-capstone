import { useForm, Create } from "@refinedev/antd";

import { Form, Input } from "antd";

export const CreateGenre= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical">
        <Form.Item label="Genre" name="genre">
          <Input />
        </Form.Item>
      </Form>
    </Create>
  );
};

