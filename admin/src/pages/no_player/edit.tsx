import { useForm, Edit } from "@refinedev/antd";

import { Form, Input, } from "antd";

export const EditNoPlayer= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Edit saveButtonProps={saveButtonProps} >
      <Form {...formProps} layout="vertical">
        <Form.Item label="No. Player" name="no_player">
          <Input />
        </Form.Item>
      </Form>
    </Edit>
  );
};

