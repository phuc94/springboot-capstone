import { useForm, Edit } from "@refinedev/antd";

import { Form, Input, } from "antd";

export const EditPlaymode= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Edit saveButtonProps={saveButtonProps} >
      <Form {...formProps} layout="vertical">
        <Form.Item label="Name" name="name">
          <Input />
        </Form.Item>
      </Form>
    </Edit>
  );
};

