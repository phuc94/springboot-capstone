import { useForm, Edit } from "@refinedev/antd";

import { Form, Input, } from "antd";

export const EditCouponType= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Edit saveButtonProps={saveButtonProps} >
      <Form {...formProps} layout="vertical">
        <Form.Item label="Type" name="type">
          <Input />
        </Form.Item>
      </Form>
    </Edit>
  );
};

