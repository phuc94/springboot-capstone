import { useForm, Create } from "@refinedev/antd";

import { Form, Input } from "antd";

export const CreateCouponType= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Create saveButtonProps={saveButtonProps}>
      <Form {...formProps} layout="vertical">
        <Form.Item label="Type" name="type">
          <Input />
        </Form.Item>
      </Form>
    </Create>
  );
};

