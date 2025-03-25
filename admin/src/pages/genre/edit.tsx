import { useForm, Edit } from "@refinedev/antd";

import { Form, Input, } from "antd";

export const EditGenre= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Edit saveButtonProps={saveButtonProps} >
      <Form {...formProps} layout="vertical">
        <Form.Item label="Genre" name="genre">
          <Input />
        </Form.Item>
      </Form>
    </Edit>
  );
};

