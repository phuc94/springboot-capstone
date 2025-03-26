import { useForm, Edit } from "@refinedev/antd";

import { Form, Input, } from "antd";

export const EditGame= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Edit saveButtonProps={saveButtonProps} >
      <Form {...formProps} layout="vertical">
        <Form.Item label="Description" name="description">
          <Input />
        </Form.Item>
      </Form>
    </Edit>
  );
};

