import { useForm, Edit } from "@refinedev/antd";
import ReactQuill from "react-quill";

import { Form, Input, } from "antd";

export const EditDescription= () => {
  const { formProps, saveButtonProps } = useForm({
    redirect: "show",
  });

  return (
    <Edit saveButtonProps={saveButtonProps} >
      <Form {...formProps} layout="vertical">
        <Form.Item label="Description" name="description">
          <ReactQuill theme="snow" />
        </Form.Item>
      </Form>
    </Edit>
  );
};

