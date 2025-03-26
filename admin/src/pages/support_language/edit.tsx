import { useForm, Edit } from "@refinedev/antd";

import { Form, Input, } from "antd";

export const EditSupportLanguage= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Edit saveButtonProps={saveButtonProps} >
      <Form {...formProps} layout="vertical">
        <Form.Item label="Language" name="language">
          <Input />
        </Form.Item>
      </Form>
    </Edit>
  );
};

